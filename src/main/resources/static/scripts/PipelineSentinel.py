import pickle
import sys
import os
import datetime
import itertools
from aenum import MultiValueEnum
import numpy as np
import geopandas as gpd
import matplotlib as mpl
import matplotlib.pyplot as plt
import matplotlib.gridspec as gridspec
from matplotlib.colors import ListedColormap, BoundaryNorm
from mpl_toolkits.axes_grid1 import make_axes_locatable
from shapely.geometry import Polygon
from tqdm.auto import tqdm
import lightgbm as lgb
import joblib
from sklearn import metrics
from sklearn import preprocessing
from eolearn.core import EOTask, EOPatch, LinearWorkflow, FeatureType, OverwritePermission, \
    LoadTask, SaveTask, EOExecutor, ExtractBandsTask, MergeFeatureTask
from eolearn.io import SentinelHubInputTask, ExportToTiff
from eolearn.mask import AddValidDataMaskTask

from eolearn.geometry import VectorToRaster, PointSamplingTask, ErosionTask
from eolearn.features import LinearInterpolation, SimpleFilterTask, NormalizedDifferenceIndexTask
from sentinelhub import UtmZoneSplitter, BBox, CRS, DataCollection
from dateutil.tz import tzutc
from matplotlib import dates
import pandas as pd
from eolearn.io  import SentinelHubEvalscriptTask
from sentinelhub import geo_utils
from sentinelhub import  bbox_to_dimensions
from eolearn.mask import CloudMaskTask
from sentinelhub import SHConfig
from numpy import float32
import datetime
from joblib import load
from sentinelhub import geo_utils
from sentinelhub import  bbox_to_dimensions
from sqlalchemy import create_engine
import psycopg2
coord1=sys.argv[1]
coord2=sys.argv[2]
coord3=sys.argv[3]
coord4=sys.argv[4]
cord1=float(coord1)
cord2=float(coord2)
cord3=float(coord3)
cord4=float(coord4)
datedeb=sys.argv[5]
datefin=sys.argv[6]
datevisu=sys.argv[7]
bbox=[cord1, cord2, cord3, cord4]
roi_bbox = BBox(bbox, crs=CRS.WGS84)
time_interval = (datedeb, datefin)
resolution = 10
class SentinelHubValidData:
    def __call__(self, eopatch):
        return np.logical_and(eopatch.mask['IS_DATA'].astype(np.bool),
                              np.logical_not(eopatch.mask['CLM'].astype(np.bool)))
class CountValid(EOTask):
   
    def __init__(self, count_what, feature_name):
        self.what = count_what
        self.name = feature_name

    def execute(self, eopatch):
        eopatch.add_feature(FeatureType.MASK_TIMELESS, self.name, np.count_nonzero(eopatch.mask[self.what],axis=0))

        return eopatch
config = SHConfig()

config.instance_id = ''
config.sh_client_id = ''
config.sh_client_secret = ''
config.save()

band_names = ['B02', 'B03', 'B04', 'B08', 'B11', 'B12']
add_data = SentinelHubInputTask(
    bands_feature=(FeatureType.DATA, 'BANDS'),
    bands = band_names,
    resolution=10,
    maxcc=0.8,
    time_difference=datetime.timedelta(minutes=120),
    data_collection=DataCollection.SENTINEL2_L1C,
    additional_data=[(FeatureType.MASK, 'dataMask', 'IS_DATA'),
                     (FeatureType.MASK, 'CLM'),
                     (FeatureType.DATA, 'CLP')],
    max_threads=5,
    config=config
)



ndvi = NormalizedDifferenceIndexTask((FeatureType.DATA, 'BANDS'), (FeatureType.DATA, 'NDVI'),
                                     [band_names.index('B08'), band_names.index('B04')])


add_sh_valmask = AddValidDataMaskTask(SentinelHubValidData(),
                                      'IS_VALID' 
                                     )

count_val_sh = CountValid('IS_VALID', 
                          'VALID_COUNT' 
                         )

path_out = './optique1/'
if not os.path.isdir(path_out):
    os.makedirs(path_out)
save = SaveTask(path_out, overwrite_permission=OverwritePermission.OVERWRITE_PATCH)
band_names = ['B02', 'B03', 'B04', 'B08', 'B11', 'B12']
add_data = SentinelHubInputTask(
    bands_feature=(FeatureType.DATA, 'BANDS'),
    bands = band_names,
    resolution=10,
    maxcc=0.8,
    time_difference=datetime.timedelta(minutes=120),
    data_collection=DataCollection.SENTINEL2_L1C,
    additional_data=[(FeatureType.MASK, 'dataMask', 'IS_DATA'),
                     (FeatureType.MASK, 'CLM'),
                     (FeatureType.DATA, 'CLP')],
    max_threads=5
)

add_clm = CloudMaskTask(data_feature='BANDS',
                        all_bands=True,
                        processing_resolution=160,
                        mono_features=('CLP', 'CLM'),
                        mask_feature=None,
                        average_over=16,
                        dilation_size=8)
workflow = LinearWorkflow(add_data,ndvi,add_sh_valmask,count_val_sh,save)
result = workflow.execute({
add_data: {'bbox': roi_bbox, 'time_interval': time_interval},
       save: {'eopatch_folder': 'eopatch'}
       
        })

configrad = SHConfig()

configrad.instance_id = ''
configrad.sh_client_id = ''
configrad.sh_client_secret = ''
configrad.save()

s1_evalscript = """
//VERSION=3
function setup() {
  return {
    input: [{
        bands:["VV", "VH" ,"dataMask"], 
        metadata: ["bounds"]
    }],
    output: [
      {
          id: "VV",
          bands: 1,
          sampleType: "FLOAT32",
          nodataValue: NaN,
      },
      {
          id: "VH",
          bands: 1,
          sampleType: "FLOAT32",
          nodataValue: NaN,
      },
      {
          id: "MASK",
          bands: 1,
          sampleType: "UINT8",
          nodataValue: 0,
      }
    ]
  };
}

    function evaluatePixel(samples) {
      var VV_log = 10 * Math.log(samples.VV) / Math.LN10;
      var VH_log = 10 * Math.log(samples.VH) / Math.LN10;

      return {
        VV: [VV_log],
        VH: [VH_log],
        MASK: [samples.dataMask]
        
      };
    }
    """

s1_processing = {
    "backCoeff": "GAMMA0_ELLIPSOID",
    "orthorectify": True,
    "demInstance": "COPERNICUS_30",
}

s1_input = SentinelHubEvalscriptTask(
    features={
        FeatureType.DATA: {'VV', 'VH'},
        FeatureType.MASK: {'MASK'}
    },
    evalscript=s1_evalscript,
    data_collection=DataCollection.SENTINEL1,
    resolution=10,
    time_difference=datetime.timedelta(minutes=120),
    aux_request_args={'processing': s1_processing},
    max_threads=5,
    config=configrad
)
path_outrad = './radar1/'
if not os.path.isdir(path_outrad):
    os.makedirs(path_outrad)
saverad = SaveTask(path_outrad, overwrite_permission=OverwritePermission.OVERWRITE_PATCH)
workflowrad = LinearWorkflow(
         s1_input,
         saverad
        )
resultt = workflowrad.execute({
       s1_input: {'bbox': roi_bbox, 'time_interval': time_interval},
       saverad: {'eopatch_folder': 'eopatch'}

        })
eopatch = result.eopatch()
eopatchh = resultt.eopatch()
"""
loc_bbox = BBox(bbox=roi_bbox, crs=CRS.WGS84)
loc_size = bbox_to_dimensions(loc_bbox, resolution=resolution)
bb_utm = geo_utils.to_utm_bbox(loc_bbox)
transf = bb_utm.get_transform_vector(resx=resolution, resy=resolution)
pix_latt = np.array(np.arange(0, loc_size[1]))
latss = np.array([pix_latt] * loc_size[0]).transpose()
pix_long = np.array(np.arange(0, loc_size[0]))
lonss = np.array([pix_long] * loc_size[1])
long, lat = geo_utils.pixel_to_utm(latss, lonss, transf)
lon_degreess, lat_degreess = geo_utils.to_wgs84(long, lat, bb_utm.crs)
Long=pd.DataFrame(lon_degreess)
coordLatt=pd.DataFrame(lat_degreess)
coordLatt.to_csv(r'./lat.csv', index = False)
la = pd.read_csv('./lat.csv')
la = la.stack().reset_index(drop=True)
la.to_csv(r'./latitude.csv', index=False)
Long.to_csv(r'./Long.csv', index = False)
lon = pd.read_csv('./Long.csv')
lon = lon.stack().reset_index(drop=True)
lon.to_csv(r'./longitude.csv', index=False)
"""
Latitude=pd.read_csv('./latitude.csv',header=None)
Latitude.columns =["lat"]
Longitude=pd.read_csv('./longitude.csv',header=None)
Longitude.columns =["long"]

dates = np.array([x.replace(tzinfo=None) for x in eopatch.timestamp])
date = datetime.datetime.strptime(datevisu,'%Y,%m,%d')
closest_date_id = np.argsort(abs(date-dates))[0]
NDVI=pd.array(eopatch.data["NDVI"][closest_date_id][...,0].flatten())
VV=np.array(eopatchh.data["VV"][closest_date_id][...,0].flatten())
VH=np.array(eopatchh.data["VH"][closest_date_id][...,0].flatten())
dx = pd.DataFrame(columns =['VV', 'VH','NDVI'])
data=dx
data['VV']=VV
data['VH']=VH
data['NDVI']=NDVI
data.to_csv(r'./data1.csv', index = False)
NDVI=pd.DataFrame(NDVI)
NDVI=NDVI.mask(np.isinf(NDVI))
NDVI.columns =["ndvi"]
NDVI=NDVI.assign(datee=datevisu)
NDVI['datee']=pd.to_datetime(NDVI.iloc[:,1].str.replace(',','-'),format='%Y-%m-%d')
dNDVI=NDVI
VV=pd.DataFrame(VV)
VV=VV.mask(np.isinf(VV))
VV.columns =["vv"]
VV=VV.assign(datee=datevisu)
VV['datee']=pd.to_datetime(VV.iloc[:,1].str.replace(',','-'),format='%Y-%m-%d')
dVV=VV
VH=pd.DataFrame(VH)
VH=VH.mask(np.isinf(VH))
VH.columns =["vh"]
VH=VH.assign(datee=datevisu)
VH['datee']=pd.to_datetime(VH.iloc[:,1].str.replace(',','-'),format='%Y-%m-%d')
dVH=VH
NDVII=pd.concat([Longitude, Latitude,dNDVI],axis=1) 
VVV=pd.concat([Longitude, Latitude,dVV],axis=1) 
VHH=pd.concat([Longitude, Latitude,dVH],axis=1) 
engine = create_engine('postgresql://postgres:1996@localhost:5432/BDproject')
NDVII.to_sql('ndvi', engine, if_exists='append',index=False)
VVV.to_sql('vv', engine, if_exists='append',index=False)
VHH.to_sql('vh', engine, if_exists='append',index=False)
dataset=pd.read_csv ('./data1.csv', decimal=',')
dataa=dataset.replace(np.nan, 0)
dataa.replace("-inf",0,inplace=True)
z = dataa.iloc[:,0:3].values
model = load('modele_rf1.pkl')
predrf1 = model.predict(z) 
SM=pd.DataFrame({'sm':predrf1})
SM=SM.assign(datee=datevisu)
SM['datee']=pd.to_datetime(SM.iloc[:,1].str.replace(',','-'),format='%Y-%m-%d')
Res=pd.concat([Longitude, Latitude,SM],axis=1) 
engine = create_engine('postgresql://postgres:1996@localhost:5432/BDproject')
Res.to_sql('sm', engine, if_exists='append',index=False)
