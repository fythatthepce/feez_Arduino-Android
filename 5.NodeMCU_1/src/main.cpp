#include<arduino.h>
#include "Calculator.h"
void setup() {
  // initialize digital pin 13 as an output.
  //pinMode(13, OUTPUT);
  Serial.begin(115200);
}

// the loop function runs over and over again forever
void loop() {
  /*
  digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
  delay(1000);              // wait for a second
  digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
  delay(1000);              // wait for a second
  */

  Calculator test;
  test.Fn1(50,50);
  Serial.println(test.Sum);
  delay(500);
}
