#include <ESP8266WiFi.h>
//set pin numbers
#define D0 16   //D0 = 16 

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
  //MOTER_A
  pinMode(D1,OUTPUT);  //ENA
  pinMode(D2,OUTPUT);  //IN1
  pinMode(D3,OUTPUT);  //IN2

   //MOTER_B
  pinMode(D6,OUTPUT);  //ENB
  pinMode(D7,OUTPUT);  //IN3
  pinMode(D8,OUTPUT);  //IN4

  
}


void forward(){
     //FORWARD
     //MOTER A
     digitalWrite(D2,HIGH);
     digitalWrite(D3,LOW);
     analogWrite(D1,255);   //speed 255
     
     
     //MOTER B
     digitalWrite(D7,HIGH);
     digitalWrite(D8,LOW);
     analogWrite(D6,255);   //speed 255
}

void backward(){
    //BACKWARD
      //MOTER A
     digitalWrite(D2,LOW);
     digitalWrite(D3,HIGH);
     analogWrite(D1,255);   //speed 255
     
     
     //MOTER B
     digitalWrite(D7,LOW);
     digitalWrite(D8,HIGH);
     analogWrite(D6,255);   //speed 255
}

void left(){
    //TURN LEFT
      //MOTER A
     digitalWrite(D2,HIGH);
     digitalWrite(D3,LOW);
     analogWrite(D1,255);   //speed 255

       //MOTER B
     digitalWrite(D7,HIGH);
     digitalWrite(D8,HIGH);
     analogWrite(D6,0);
}

void right(){

    //MOTER B
     digitalWrite(D7,HIGH);
     digitalWrite(D8,LOW);
     analogWrite(D6,255);  //speed 255
     
      //MOTER A
     digitalWrite(D2,HIGH);
     digitalWrite(D3,HIGH);
     analogWrite(D1,0);   
}




void loop() {

  
     forward();
     delay(3000); //TIME FORWARD  = 3 SEC    
     
     left(); 
     delay(3000); //TIME TURNLEFT  = 3 SEC    
}
