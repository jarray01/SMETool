# SMETool
 <p align="justify"> Earth Observation (EO) technologies have played an increasingly importantrole in monitoring the Sustainable Development Goals (SDG). 
These technologies often combined with Machine Learning (ML) models provide efficientmeans for achieving the SDGs. 
The great progress of this combination is alsodemonstrated by the large number of software, web tools and packages thathave been made available for free use. 
This paper presents a web-based tool for Soil Moisture Estimation (SMETool), designed for the soil moisture estimation using Sentinel-1A and Sentinel-2A data based on Eo-learn library. SMETool implements several ML techniques such as (Artificial Neural Net-work (ANN), Random Forest (RF), Convolutional Neural Network (CNN),etc.). 
The SMETool could be very useful for decision makers in the region inassessing the effects of drought and desertification events.
Experiments werecarried out on two sites in Tunisia during the period from 2016 to 2017. 
The CNN allowed obtaining the best results in terms of performance and precision, compared to other ML methods. 
The achieved results reveal that the soil moisture,  was highly correlated to the in-situ measurements with high Pearson’s correlation coefficient R ( RRF= 0.86, RANN= 0.75, RXGBoost= 0.79,RCNN= 0.87 ) and low Root Mean Square Error (RMSE) (RMSERF= 1.09m3/m3, RMSEANN= 1.49 m3/m3, RMSEXGBoost= 1.39 m3/m3, RMSECNN=1.12 m3/m3), respectively.</p>

![methodo-software](https://user-images.githubusercontent.com/38008180/154925003-34d99c26-e7f0-4ecb-bf41-aec55e938973.png)

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
To view the python file that uses the eolearn packages : src/main/resources/static/scripts
