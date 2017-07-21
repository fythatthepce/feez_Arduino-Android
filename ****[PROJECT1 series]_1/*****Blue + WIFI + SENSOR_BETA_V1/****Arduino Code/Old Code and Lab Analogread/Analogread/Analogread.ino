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

const int ldr  = A0;
int ldr_value  = 0;

int led = D1; 

void setup() {
   Serial.begin(9600);
    pinMode(led,OUTPUT); 
}

void loop() {
   ldr_value = analogRead(ldr);
   
   if(ldr_value != 1024)
   {
     digitalWrite(led,HIGH);
   }else{
    digitalWrite(led,LOW);
   }

   Serial.println(ldr_value );
   delay(1000); //wait
}
