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
  pinMode(D1,OUTPUT);
}

void loop() {
  // put your main code here, to run repeatedly:
  digitalWrite(D1,HIGH);
  delay(500);
  digitalWrite(D1,LOW);
  delay(500);
}
