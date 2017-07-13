#include <ESP8266WiFi.h>

//////////////////////
// WiFi Definitions //
//////////////////////

const char WiFiName[] = "FEEz_MCU";
const char WiFiPass[] = "";


/////////////////////
// Pin Definitions //
/////////////////////
const int LED_PIN = 5; // Thing's onboard, green LED

WiFiServer server(80);

void setup() 
{
  //initHardware();
  Serial.begin(115200);
  pinMode(LED_PIN, OUTPUT);
  
  //setupWiFi();
  WiFi.softAP(WiFiName, WiFiPass);
  server.begin();
}

void loop() 
{
  // Check if a client has connected
  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  
  // Read the first line of the request
  String req = client.readStringUntil('\r');
  Serial.println(req);
  client.flush();

  // Match the request
  int val = -1; // We'll use 'val' to keep track of both the
                // request type (read/set) and value if set.
  if (req.indexOf("/led/0") != -1)
    val = 0; // Will write LED low
  else if (req.indexOf("/led/1") != -1)
    val = 1; // Will write LED high
  if (val == 1)
    digitalWrite(LED_PIN,HIGH);

  else if (val == 0)
  digitalWrite(LED_PIN,LOW);
  

  client.flush();
  


  //HTML TO REPORT IN WEB
  
  String s = "HTTP/1.1 200 OK\r\n";
  s += "Content-Type: text/html\r\n\r\n";
  s += "<!DOCTYPE HTML>\r\n<html>\r\n";
  
  s += "<h1>Control LED</h1>\r\n";
  
  s += "<p>\r\n";
  s += "<a href=\"/led/1\">\r\n";
  s += "<button>LED On</button>\r\n";
  s += "</a>\r\n";
  s += "</p>\r\n";

   s += "<p>\r\n";
  s += "<a href=\"/led/0\">\r\n";
  s += "<button>LED Off</button>\r\n";
  s += "</a>\r\n";
  s += "</p>\r\n";

  if (val == 1)
  {
    s += "LED is now ";
  }

  else if (val == 0)
  {
    s += "LED is off ";
  }
  
  else
  {
    s += "Invalid Request.<br> Try /led/1, /led/0, or /read.";
  }
  s += "</html>\n";

  client.print(s);
  delay(1);
  Serial.println("Client disonnected");
}

