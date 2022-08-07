# SMETool   &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; &ensp;  &ensp; &ensp; &ensp; &ensp; &ensp; &ensp; ![dataflow](https://github.com/jarray01/SMETool/blob/main/src/main/resources/static/assets/images/logo/logosmesys.png)

 <p align="justify">
 SMETool stands for Soil Moisture Estimation Tool. It is a complete open source and web based for soil moisture estimation  platform using Machine Learning methods and EO-Learn framework. It is a generic pipeline  for soil moisture estimation using earth observation data.
<ul>
<li> It combine ML techniques, EO data and Eo-Learn library. </li>
<li> The main workflow  has main data download and processing  functions  using EO-Learn library. </li>
<li> It is  easily customizable and extensible thanks to a high number of adds-on and web services to integrate.</li>
<li>It  offers mass import tools to help you being even more efficient soil moisture estimation task.</li>
<li>It allows the user to access the web application through a web interface and use the map viewer to select any location in the study area  in order to make queries about the geographic database. </li>
</ul>
 </p>

![methodo-software](https://user-images.githubusercontent.com/38008180/154925003-34d99c26-e7f0-4ecb-bf41-aec55e938973.png)
<center> SMETool development architecture, S-1A: Sentinel-1A images and S-2A: Sentinel-2A. </center>
<ol>
  <li value="1"> SMETool data flow</li>
  ![dataflow](https://user-images.githubusercontent.com/38008180/154926477-386de055-a66e-458f-8442-b2516a7e2ac6.png)
The requests sent and received by the user establish a link with several197processes that are executed in the background without the intervention of198the user (steps 1 and 5).  The services executed in the background are the operations applied to process the satellite images preprocessing, the ML training models and the storage data in a geospatial database (steps 2,3 and 4)
  <li value="2">SMETool Structure</li>
  The SMETool service is composed of six main components illustrated as follows
  ![structure](https://user-images.githubusercontent.com/38008180/154927422-769aae5b-50c6-4027-a162-a24c786d924b.png)

  <li value="3">Home interface</li>
  The SMETool home interface, as presented follows
  ![home](https://user-images.githubusercontent.com/38008180/154927939-4dcd8f91-92cf-4e87-b49d-b85a68a57f7d.png)

  <li value="4">Input data interface</li>
  The SMETool input selection interface is shown as follows
  ![input di](https://user-images.githubusercontent.com/38008180/154928113-238b42ea-4f5d-4503-9022-4f169a10c716.png)

  <li value="5">Results visualization interface</li>
  The SMETool results, which naturally includes all previous information, can be further accessed by results visualisation interface
  ![resultats](https://user-images.githubusercontent.com/38008180/154928390-e444ebf1-2992-4bdc-ba05-7e296b214889.png)

</ol>
ML techniques files can be found at : https://github.com/jarray01/SMETool/tree/main/ML%20techniques <br>
The pipeline python file that uses the eolearn packages can be found at : https://github.com/jarray01/SMETool/tree/main/Complete%20pipeline%20script   <br>
To see list of available web services, see  : https://github.com/jarray01/SMETool/blob/main/src/main/java/tn/noureddine/controller/Shapecontroller.java
