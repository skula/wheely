#include <SoftwareSerial.h>
SoftwareSerial mySerial(10, 11); // RX, TX
int index = 0;
char msg[9];
int pos = 0;
void setup()  
{
  // Open serial communications and wait for port to open:
  Serial.begin(57600);
  Serial.println("Hello Slown");

  // set the data rate for the SoftwareSerial port
  mySerial.begin(9600);
  
}

void loop() // run over and over
{if (mySerial.available())
  {
    char c = mySerial.read();
    msg[index] = c;
    index ++;
  }
  
  if(index==8){
     msg[index] = '\0';
     char * command = strtok(msg, ":");
     while(command != 0){
       int speedInt = atoi(command);
       Serial.println(speedInt);
       command = strtok(0, ":");
     }
     index = 0;
  }
  
}
