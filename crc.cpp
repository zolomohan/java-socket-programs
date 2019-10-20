#include <iostream.h>
#include <conio.h>

void main()
{
  int i, j, k, l;

  //Get Frame
  int frameSize;
  cout << "\n Enter Frame size: ";
  cin >> frameSize;

  int frame[20];

  cout << "\n Enter Frame:";
  for (i = 0; i < frameSize; i++)
    cin >> frame[i];

  //Get Generator
  int generatorSize;
  cout << "\n Enter Generator size: ";
  cin >> generatorSize;

  int generator[20];

  cout << "\n Enter Generator:";
  for (i = 0; i < generatorSize; i++)
    cin >> generator[i];

  cout << "\n Sender Side:";
  cout << "\n Frame: ";
  for (i = 0; i < frameSize; i++)
    cout << frame[i];

  cout << "\n Generator :";
  for (i = 0; i < generatorSize; i++)
    cout << generator[i];

  //Append 0's
  int rs = generatorSize - 1;
  cout << "\n Number of 0's to be appended: " << rs;
  for (i = frameSize; i < frameSize + rs; i++)
    frame[i] = 0;

  //Copy Frame to Temp
  int temp[20];
  for (i = 0; i < 20; i++)
    temp[i] = frame[i];

  cout << "\n Message after appending 0's :";
  for (i = 0; i < frameSize + rs; i++)
    cout << temp[i];

  //Division
  for (i = 0; i < frameSize; i++)
  {
    j = 0;
    k = i;
    //check whether it is divisible or not
    if (temp[k] >= generator[j])
      for (j = 0, k = i; j < generatorSize; j++, k++)
        if ((temp[k] == 1 && generator[j] == 1) || (temp[k] == 0 && generator[j] == 0))
          temp[k] = 0;
        else
          temp[k] = 1;
  }

  //CRC
  int crc[15];
  for (i = 0, j = frameSize; i < rs; i++, j++)
    crc[i] = temp[j];

  cout << "\n CRC bits: ";
  for (i = 0; i < rs; i++)
    cout << crc[i];

  cout << "\n Transmitted Frame: ";
  int tf[15];
  for (i = 0; i < frameSize; i++)
    tf[i] = frame[i];
  for (i = frameSize, j = 0; i < frameSize + rs; i++, j++)
    tf[i] = crc[j];
  for (i = 0; i < frameSize + rs; i++)
    cout << tf[i];

  cout << "\n Receiver side : ";
  cout << "\n Received Frame: ";
  for (i = 0; i < frameSize + rs; i++)
    cout << tf[i];

  for (i = 0; i < frameSize + rs; i++)
    temp[i] = tf[i];

  //Division
  for (i = 0; i < frameSize + rs; i++)
  {
    j = 0;
    k = i;
    if (temp[k] >= generator[j])
      for (j = 0, k = i; j < generatorSize; j++, k++)
        if ((temp[k] == 1 && generator[j] == 1) || (temp[k] == 0 && generator[j] == 0))
          temp[k] = 0;
        else
          temp[k] = 1;
  }

  cout << "\n Reaminder: ";
  int rrem[15];
  for (i = frameSize, j = 0; i < frameSize + rs; i++, j++)
    rrem[j] = temp[i];
  for (i = 0; i < rs; i++)
    cout << rrem[i];

  int flag = 0;
  for (i = 0; i < rs; i++)
    if (rrem[i] != 0)
      flag = 1;

  if (flag == 0)
    cout << "\n Since Remainder Is 0 Hence Message Transmitted From Sender To Receriver Is Correct";
  else
    cout << "\n Since Remainder Is Not 0 Hence Message Transmitted From Sender To Receriver Contains Error";
  getch();
}