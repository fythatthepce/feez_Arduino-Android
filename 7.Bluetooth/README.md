# Bluetooth<br>
<br>

1.ตั้งชื่อและพาสของ Bluetooth HC-05<br>
ref:http://howtomechatronics.com/tutorials/arduino/arduino-and-hc-05-bluetooth-module-tutorial/<br>
ref:http://blog.redlinesoft.net/?p=3003<br>
connect : <br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/b1.png"/>
<br>
*** ให้เข้าสู่ setting mode ของ HC05 โดยการกดโมดูลค้างไว้ แล้วจึงค่อยต่อขา VCC เข้าไป ส่วน EN และขา STATE ปล่อยว่างไว้ ***
<br>
<br>#include <SoftwareSerial.h>
<br>
<br>SoftwareSerial mySerial(10, 11); // RX, TX
<br>
<br>void setup()
<br>{
<br>  Serial.begin(38400);
<br>  while (!Serial) ;
<br>  mySerial.begin(38400);
<br>}
<br>
<br>void loop()
<br>{
<br>  if (mySerial.available())
<br>    Serial.write(mySerial.read());
<br>  if (Serial.available())
<br>    mySerial.write(Serial.read());
<br>}
<br>
<br>
Setting : <br>
ลองพิมพ์ AT จากนั้นกด Enter จะขึ้นว่า OK หรือ ERROR แสดงว่าต่อวงจรถูกแล้ว และโมดูลอยู่ในโหมดพร้อมรับคำสั่งตั้งค่าแล้ว<br>
คำสั่งเปลี่ยนชื่ออุปกรณ์ คือคำสั่ง AT+NAME="Device Name" เช่น หากต้องการเปลี่ยนชื่อเป็น IOXhop HC5 ต้องพิมพ์ว่า AT+NAME="IOXhop HC5" แล้วกด Enter หากสำเร็จจะขึ้น OK

<br>
<br>
เปลี่ยนพาสที่ใช้จับคู่ ใช้คำสั่ง AT+PSWD=New Password เช่น ต้องการตั้งค่าเป็นรหัส 9876 ต้องใช้คำสั่ง AT+PSWD=9876 ส่วนใน project นี้ใช้ 45xx <br>
*** เอาขา VCC ออก แล้วเสียบเข้าไปใหม่แล้วให้โทรศัพท์ค้นหาชื่อ Bluetooth เป็นอันเสร็จ ***
<br><br>
Connect Mobile test by Bluetooth Terminal ไม่จำเป็นต้องทดสอบก็ได้ ข้ามไปทำ part android เลย : <br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/b3.png"/>
<br><br>
จากนั้นไปโหลดแอพ Bluetooth Terminal มา แอพนี้จะใช้สำหรับส่งข้อมูลไปให้โมดูล HC05 สามารถโหลดได้ที่ : https://play.google.com/store/apps/details?id=Qwerty.BluetoothTerminal&hl=th<br>

 เปิดแอพขึ้นมา จะพบกับหน้าต่างง่ายๆ หากชื่อในช่องตรงกับชื่อของโมดูล HC05 ที่ได้ตั้งไว้ ก็สามารถกด Connect ได้เลย
<br>
<br>
<br>
2.app + arduino<br>
ref:http://www.instructables.com/id/Modify-The-HC-05-Bluetooth-Module-Defaults-Using-A/<br>
ref:http://www.instructables.com/id/Android-Bluetooth-Control-LED-Part-2/<br>
ref:http://www.instructables.com/id/Android-Aplication-for-Robot-Control-Using-HC-05-a/<br>
ref:https://www.codeproject.com/Articles/814814/Android-Connectivity<br>
<br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/android-studio-logo.png"/><br><br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/blue1.png"/><br><br>
เมื่อตั้งชื่อ และ รหัสของ Bluetooth ต่อไปจะทำให้ android application ควบคุม arduino โดยให้ทำการเขียน android application และ เขียนโค้ด arduino โดยให้เราทำการอัพโค้ดลง arduino ก่อนที่จะต่อ vcc เข้า HC-05(Bluetooth) เมื่ออัพโค้ดเสร็จให้ต่อ vcc เข้าตามปกติ แล้วทำการทดสอบ
<br><br>
<img src="https://github.com/fythatthepce/feez_Arduino-Android/blob/master/Pictures/pic_blue_led.png"/>







