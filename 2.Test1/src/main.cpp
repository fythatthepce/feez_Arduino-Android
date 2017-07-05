#include "Arduino.h"

// Set variable
#define a 10
//int a = 10;

void setup()
{
  // initialize LED digital pin as an output.
  pinMode(a, OUTPUT);
}

void loop()
{
  // turn the LED on (HIGH is the voltage level)
  digitalWrite(a, HIGH);

  // wait for a second
  delay(200);

  // turn the LED off by making the voltage LOW
  digitalWrite(a, LOW);

   // wait for a second
  delay(200);

}
