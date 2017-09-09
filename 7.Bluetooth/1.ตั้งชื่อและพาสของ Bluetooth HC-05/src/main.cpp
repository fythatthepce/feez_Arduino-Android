#include <Arduino.h>
#include <SoftwareSerial.h>

SoftwareSerial mySerial(10, 11); // 10 ต่อกับ TX ใน HC05, 11 ต่อกับ RX ใน HC05

void setup()
{
  Serial.begin(38400);
  while (!Serial) ;
  mySerial.begin(38400);
}

void loop()
{
  if (mySerial.available())
    Serial.write(mySerial.read());
  if (Serial.available())
    mySerial.write(Serial.read());
}
