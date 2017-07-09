#include <ESP8266WiFi.h>
//set pin numbers
#define D0 16  //D0 = 16 

//pwm D1~D12
#define D1 5  
#define D2 4  
#define D3 0  
#define D4 2 
#define D5 14  
#define D6 12
#define D7 13
#define D8 15



void setup() {
  // put your setup code here, to run once:
  //MOTER_A
  pinMode(D1,OUTPUT);  //ENA
  pinMode(D2,OUTPUT);  //IN1
  pinMode(D3,OUTPUT);  //IN2

   //MOTER_B
  pinMode(D6,OUTPUT);  //ENB
  pinMode(D7,OUTPUT);  //IN3
  pinMode(D8,OUTPUT);  //IN4

  
}

void loop() {
  // put your main code here, to run repeatedly:
  /*
  digitalWrite(D1,HIGH);
  delay(500);
  digitalWrite(D1,LOW);
  delay(500);*/

      //MOTER A
     //motor move Forward with speed
     digitalWrite(D2,HIGH);
     digitalWrite(D3,LOW);
     analogWrite(D1,255);   //speed 255


     //MOTER B
     //motor move Forward with speed
     digitalWrite(D7,HIGH);
     digitalWrite(D8,LOW);
     analogWrite(D6,255);   //speed 255

  
}
