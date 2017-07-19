#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

//pwm D1~D12
#define D1 5  
#define D2 4  
#define D3 0  
#define D4 2 
#define D5 14  
#define D6 12
#define D7 13
#define D8 15


//set BTSerial D4 =  RX , D8 = TX 
SoftwareSerial BTSerial(D4, D8); // RX | TX//set BTSerial D4 =  RX , D8 = TX 

int led = D1;
char inbyte = 0;



float voltageValue[4] = {0,1,2,3};
void setup() {
  pinMode(led, OUTPUT);
}


void loop() {

  /*for ultrasonic sonic
  int cm = 14;
  if(cm == 15){
    sendAndroidValues();    //GREEN PIC Value = 1;
  }else{
    sendAndroidValue2();   //RED PIC Value = 0;
  }*/
  
  //sendAndroidValues();
  if (BTSerial.available() > 0)
  {
    inbyte = BTSerial.read();
    if (inbyte == '0')
    {
      //LED off
      digitalWrite(led, LOW);
    }
    if (inbyte == '1')
    {
      //LED on
      digitalWrite(led, HIGH);
    }
    
    if (inbyte == '2')  //PUSH
    {
      sendAndroidValues();  //1
    }  

     if (inbyte == '3')  //RELEASE
    {
      sendAndroidValues2(); //0
    } 
   
    
  }
  delay(50);
}

void sendAndroidValues()
 {
  //puts # before the values so our app knows what to do with the data
  BTSerial.print('#');
  //for loop cycles through 4 sensors and sends values via serial
  //for(int k=0; k<4; k++)
  //{
  BTSerial.print(1);
  BTSerial.print('+');
    //technically not needed but I prefer to break up data values
    //so they are easier to see when debugging
  //}
 BTSerial.print('~'); //used as an end of transmission character - used in app for string length
 BTSerial.println();
 delay(20);        //added a delay to eliminate missed transmissions
}

void sendAndroidValues2()
 {
  //puts # before the values so our app knows what to do with the data
  BTSerial.print('#');
  //for loop cycles through 4 sensors and sends values via serial
  //for(int k=0; k<4; k++)
  //{
  BTSerial.print(0);
  BTSerial.print('+');
    //technically not needed but I prefer to break up data values
    //so they are easier to see when debugging
  //}
 BTSerial.print('~'); //used as an end of transmission character - used in app for string length
 BTSerial.println();
 delay(20);        //added a delay to eliminate missed transmissions
}
