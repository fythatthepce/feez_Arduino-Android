#include <ESP8266WiFi.h>
//pwm D1~D12
#define D1 5  
#define D2 4  
#define D3 0  
#define D4 2 
#define D5 14  
#define D6 12
#define D7 13
#define D8 15


//motor A
#define ENA D1
#define IN1 D2
#define IN2 D3

//motor B
#define ENB D5
#define IN3 D6
#define IN4 D7




void setup() {
  //MOTER_A
  pinMode(ENA,OUTPUT);  //ENA
  pinMode(IN1,OUTPUT);  //IN1
  pinMode(IN2,OUTPUT);  //IN2

  //MOTER_B
  pinMode(ENB,OUTPUT);  //ENB
  pinMode(IN3,OUTPUT);  //IN3
  pinMode(IN4,OUTPUT);  //IN4
}



void loop() {
  
}

//function

void forward(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,LOW);
     analogWrite(ENB,1023);   //speed 1023 //max cycle NodeMCU
}

void backward(){
     digitalWrite(IN3,LOW);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,1023);  
}

void right(){
     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,LOW);
     analogWrite(ENA,1023);   //speed 1023 //max cycle NodeMCU
}

void left(){
     digitalWrite(IN1,LOW);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,1023);   //speed 1023 //max cycle NodeMCU
}

void stop_motor(){
     digitalWrite(IN3,HIGH);
     digitalWrite(IN4,HIGH);
     analogWrite(ENB,0);   


     digitalWrite(IN1,HIGH);
     digitalWrite(IN2,HIGH);
     analogWrite(ENA,0);  
}

void test_sys(){
     forward();
     delay(2000); //forward 2 sec

     backward();
     delay(2000);

     left();
     delay(1000);

     right();
     delay(1000);

     stop_motor();
     delay(5000);
}


  
