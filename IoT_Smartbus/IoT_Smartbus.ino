#include <ESP8266WiFi.h>
int LED = D0;
int BUTTON = D1;

//new 2
int LED2 = D2;
int BUTTON2 = D3;

//new 3
int LED3 = D6;
int BUTTON3 = D7;

//new4
int LED4 = D8;
int BUTTON4 = D9;


//ULTRA
int pingPin = D4;
int inPin = D5;


//const char* ssid = "IoT Smart City_2";
//const char* password = "2SmartCity2";

const char* ssid = "My ASUS";
const char* password = "dickhead";

unsigned char status_led = 0;

//new2
unsigned char status_led2 = 0;

//new3
unsigned char status_led3 = 0;


//new4
unsigned char status_led4 = 0;



//position set1
unsigned char status_position1 = 0;

//position set2
unsigned char status_position2 = 0;


//position set3
unsigned char status_position3 = 0;


//position set4
unsigned char status_position4 = 0;

//position  final
unsigned char status_position_final = 0;

WiFiServer server(80);


void setup() {
  //Serial.begin(115200);
  Serial.begin(9600);

  pinMode(LED, OUTPUT);
  pinMode(BUTTON, INPUT_PULLUP);

  //new2
  pinMode(LED2, OUTPUT);
  pinMode(BUTTON2, INPUT_PULLUP);


  //new3
  pinMode(LED3, OUTPUT);
  pinMode(BUTTON3, INPUT_PULLUP);

  //new4
  pinMode(LED4, OUTPUT);
  pinMode(BUTTON4, INPUT_PULLUP);



  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
  server.begin();
  Serial.println("Server started");
  Serial.println(WiFi.localIP());


}

void loop() {

  //ULTRA
  long duration, cm;

  pinMode(pingPin, OUTPUT);
  digitalWrite(pingPin, LOW);
  delayMicroseconds(2);
  digitalWrite(pingPin, HIGH);
  delayMicroseconds(5);
  digitalWrite(pingPin, LOW);
  pinMode(inPin, INPUT);
  duration = pulseIn(inPin, HIGH);

  cm = microsecondsToCentimeters(duration);

  Serial.print(cm);
  Serial.print("cm");
  Serial.println();
  delay(500);

  if (digitalRead(BUTTON) == LOW) {
    //test:
    status_led = 1;
    digitalWrite(LED, HIGH);
    status_position_final=1;
   
  }

  //new 2

  if (digitalRead(BUTTON2) == LOW) {
    status_led2 = 1;
    digitalWrite(LED2, HIGH);
   status_position_final=1;
  }

  WiFiClient client = server.available();
  if (!client) {
    return;
  }

  Serial.println("new client");
  while (!client.available())
  {
    delay(1);
  }
  String req = client.readStringUntil('\r');
  Serial.println(req);
  client.flush();

  // if (req.indexOf("/ledoff") != -1)
  //{
  // status_led=0;
  //digitalWrite(LED,LOW);
  //}

  //ULTRA
  //if (cm == 10)
  //{
  //status_led=0;
  //digitalWrite(LED,LOW);
  //}

  //if (cm == 20)
  //{
  //status_led2=0;
  //digitalWrite(LED2,LOW);
  //}


  //new 2
  //if(req.indexOf("/ledoff2") != -1)
  //{
  // status_led2=0;
  //digitalWrite(LED2,LOW);
  //}


  //เก็บ Code HTML ลงในตัวแปรสตริง web
  String web = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
  web += "<html>\r\n";
  //web += "<meta http-equiv=\"refresh\" content=\"2;url=http://192.168.137.124\" >\r\n";
  web += "<meta http-equiv=\"refresh\" content=\"2;url=index.html\" >\r\n";
  web += "<body>\r\n";
  web += "<head>\r\n";
  web += "<h1>Smart Bus : For Driver</h1>\r\n";

  web += "<style>\r\n";
  web += ".circle-gray,.circle-yellow,.circle-red,.circle-blue,.circle-green\r\n";
  web += "{width: 50px;\r\n";
  web += "height: 50px;\r\n";
  web += "border-radius: 50%;}\r\n";
  web += ".circle-gray\r\n";
  web += "{background-color: gray}\r\n";

  web += ".circle-yellow\r\n";
  web += "{background-color: yellow}\r\n";

  web += ".circle-red\r\n";
  web += "{background-color: red}\r\n";

  web += ".circle-blue\r\n";
  web += "{background-color: blue}\r\n";

  web += ".circle-green\r\n";
  web += "{background-color: green}\r\n";

  web += "</style>\r\n";

  web += "</head>\r\n";
  web += "<p>\r\n";


  if (status_led == 1) {

    status_position1 = 1;
    web += "ป้ายสถานีที่ 1 มีคนเรียก\r\n";
    web += "<div class=\"circle-red\"></div>\r\n";


    web += "</p>\r\n";
    web += "<p>\r\n";

    web += "</p>\r\n";
    web += "<p>\r\n";

    //web += "<a href=\"/ledoff\">\r\n";
    //web += "<button>รับผู้โดยสารเรียบร้อย</button>\r\n";

    if (cm <= 6 && cm >= 3) {

      //LED off
      status_led = 0;
      digitalWrite(LED, LOW);
      status_position_final = 0;
    }

  }
  else {
    web += "สถานีที่ 1\r\n";
    web += "<div class=\"circle-gray\"></div>\r\n";
  }

  web += "</p>\r\n";
  web += "<p>\r\n";

  web += "</p>\r\n";
  web += "<p>\r\n";



  //new2
  if (status_led2 == 1) {

    status_position2 = 1;
    web += "</p>\r\n";
    web += "<p>\r\n";

    web += "</p>\r\n";
    web += "<p>\r\n";

    web += "ป้ายสถานีที่ 2 มีคนเรียก\r\n";
    web += "<div class=\"circle-red\"></div>\r\n";

    web += "</p>\r\n";
    web += "<p>\r\n";

    web += "</p>\r\n";
    web += "<p>\r\n";

    //web += "<a href=\"/ledoff2\">\r\n";
    //web += "<button>รับผู้โดยสารเรียบร้อย</button>\r\n";

    if (cm <= 15 && cm >= 6) {

      //LED2 off
      status_led2 = 0;
      digitalWrite(LED2, LOW);
      status_position_final = 0;
    }
  }
  else {
    //web += "-\r\n";
    web += "สถานีที่ 2\r\n";
    web += "<div class=\"circle-gray\"></div>\r\n";
  }

  web += "</p>\r\n";
  web += "<p>\r\n";


  web += "</a>\r\n";
  web += "</p>\r\n";

  //new3
  if (status_led3 == 1) {
    status_position3 = 1;
    web += "ป้ายสถานีที่ 3 มีคนเรียก\r\n";
    web += "<div class=\"circle-red\"></div>\r\n";


    web += "</p>\r\n";
    web += "<p>\r\n";

    web += "</p>\r\n";
    web += "<p>\r\n";

    //web += "<a href=\"/ledoff3\">\r\n";
    //web += "<button>รับผู้โดยสารเรียบร้อย</button>\r\n";
  }
  else {
    web += "สถานีที่ 3\r\n";
    web += "<div class=\"circle-gray\"></div>\r\n";
  }

  web += "</p>\r\n";
  web += "<p>\r\n";

  web += "</p>\r\n";
  web += "<p>\r\n";


  //new4
  if (status_led4 == 1) {
    status_position1 = 1;
    web += "ป้ายสถานีที่ 4 มีคนเรียก\r\n";
    web += "<div class=\"circle-red\"></div>\r\n";


    web += "</p>\r\n";
    web += "<p>\r\n";

    web += "</p>\r\n";
    web += "<p>\r\n";

    //web += "<a href=\"/ledoff4\">\r\n";
    //web += "<button>รับผู้โดยสารเรียบร้อย</button>\r\n";
  }
  else {
    web += "สถานีที่ 4\r\n";
    web += "<div class=\"circle-gray\"></div>\r\n";
  }

  //end



  web += "</p>\r\n";
  web += "<p>\r\n";

  web += "</p>\r\n";
  web += "<p>\r\n";


  web += "</p>\r\n";
  web += "<p>\r\n";


  web += "</a>\r\n";
  web += "</p>\r\n";


  web += "</p>\r\n";
  web += "<p>\r\n";


  web += "</a>\r\n";
  web += "</p>\r\n";

  web += "</p>\r\n";
  web += "<p>\r\n";


  web += "</a>\r\n";
  web += "</p>\r\n";


  web += "</p>\r\n";
  web += "<p>\r\n";


  web += "</a>\r\n";
  web += "</p>\r\n";


  //new update

  //web += "<h1>Smart Bus : For User Moniter Control</h1>\r\n";
  //web += "</a>\r\n";
  //web += "</p>\r\n";
  //web += "<a href=\"/ledon\">\r\n";
  //web += "<button>ต้องการลงสถานี 1</button>\r\n";
  //web += "</a>\r\n";
  //web += "</p>\r\n";



  //if(req.indexOf("/ledon") != -1)
  //{
  //     web += "<div class=\"circle-red\"></div>\r\n";
  //     goto test;

  //}



  //end newupdate

  web += "<h1>Smart Bus : For User</h1>\r\n";

if(status_position_final!=0)
{
  Serial.println("PASS");

  if (status_position1 == 1) {
    web += "ปัจจุบันท่านอยู่สถานีที่ 1\r\n";



    web += "</a>\r\n";
    web += "</p>\r\n";



    web += "</a>\r\n";
    web += "</p>\r\n";


    if (cm <= 6 && cm >= 3) {
      web += "อยู่สถานีที่ 1\r\n";
      web += "<div class=\"circle-green\"></div>\r\n";
      delay(1000);
    if(status_led ==1)
     status_position_final=1;
    } else {
      web += "สถานีที่ 1\r\n";
      web += "<div class=\"circle-yellow\"></div>\r\n";
    }

    web += "</a>\r\n";
    web += "</p>\r\n";

    web += "</a>\r\n";
    web += "</p>\r\n";


    if (cm <= 15 && cm > 6 ) {
      web += "อยู่สถานีที่ 2\r\n";
      web += "จะถึงที่ท่านอยู่ประมาณ 3 นาที\r\n";
      web += "<div class=\"circle-green\"></div>\r\n";
      
    } else {
      web += "สถานีที่ 2\r\n";
      web += "<div class=\"circle-gray\"></div>\r\n";
     
    }

    web += "</a>\r\n";
    web += "</p>\r\n";

    web += "</a>\r\n";
    web += "</p>\r\n";


    if (cm <= 35 && cm > 25 ) {
      web += "อยู่สถานีที่ 3\r\n";
      web += "จะถึงที่ท่านอยู่ประมาณ 5 นาที\r\n";
      web += "<div class=\"circle-green\"></div>\r\n";
    } else {
      web += "สถานีที่ 3\r\n";
      web += "<div class=\"circle-gray\"></div>\r\n";
    }

    web += "</a>\r\n";
    web += "</p>\r\n";

    web += "</a>\r\n";
    web += "</p>\r\n";

    if (cm <= 55 && cm > 48 ) {
      web += "อยู่สถานีที่ 4\r\n";
      web += "จะถึงที่ท่านอยู่ประมาณ 10 นาที\r\n";
      web += "<div class=\"circle-green\"></div>\r\n";
    } else {
      web += "สถานีที่ 4\r\n";
      web += "<div class=\"circle-gray\"></div>\r\n";
    }

  } else  if (status_position2 == 1) {
    web += "ปัจจุบันท่านอยู่สถานีที่ 2\r\n";


    web += "</a>\r\n";
    web += "</p>\r\n";



    web += "</a>\r\n";
    web += "</p>\r\n";


    if (cm <= 6 && cm > 3) {
      web += "อยู่สถานีที่ 1\r\n";
      web += "จะถึงที่ท่านอยู่ประมาณ 3 นาที\r\n";
      web += "<div class=\"circle-green\"></div>\r\n";
     

    } else {
      web += "สถานีที่ 1\r\n";
      web += "<div class=\"circle-gray\"></div>\r\n";
    }

    web += "</a>\r\n";
    web += "</p>\r\n";

    web += "</a>\r\n";
    web += "</p>\r\n";


    if (cm <= 15 && cm > 6 ) {
      web += "อยู่สถานีที่ 2\r\n";
      //web += "จะถึงที่ท่านอยู่ประมาณ 3 นาที\r\n";
      web += "<div class=\"circle-green\"></div>\r\n";
       delay(1000);
       if(status_led2 ==1)
     status_position_final=1;
    } else {
      web += "สถานีที่ 2\r\n";
      web += "<div class=\"circle-yellow\"></div>\r\n";
    }

    web += "</a>\r\n";
    web += "</p>\r\n";

    web += "</a>\r\n";
    web += "</p>\r\n";


    if (cm <= 35 && cm > 25 ) {
      web += "อยู่สถานีที่ 3\r\n";
      web += "จะถึงที่ท่านอยู่ประมาณ 3 นาที\r\n";
      web += "<div class=\"circle-green\"></div>\r\n";
    } else {
      web += "สถานีที่ 3\r\n";
      web += "<div class=\"circle-gray\"></div>\r\n";
    }

    web += "</a>\r\n";
    web += "</p>\r\n";

    web += "</a>\r\n";
    web += "</p>\r\n";

    if (cm <= 55 && cm > 48 ) {
      web += "อยู่สถานีที่ 4\r\n";
      web += "จะถึงที่ท่านอยู่ประมาณ 5 นาที\r\n";
      web += "<div class=\"circle-green\"></div>\r\n";
    } else {
      web += "สถานีที่ 4\r\n";
      web += "<div class=\"circle-gray\"></div>\r\n";
    }

    //end 2

  }//end position final


  }



  web += "</a>\r\n";
  web += "</body>\r\n";
  web += "</html>\r\n";
  client.print(web);
}

long microsecondsToCentimeters(long microseconds)
{
  // The speed of sound is 340 m/s or 29 microseconds per centimeter.
  // The ping travels out and back, so to find the distance of the
  // object we take half of the distance travelled.
  return microseconds / 29 / 2;
}







