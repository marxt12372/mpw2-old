package inputListener;

import org.lwjgl.input.Keyboard;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class TextFieldFiller
{
	private static boolean[] _pressedKey = new boolean[200];

	//private static List<Integer> _validKeys = new ArrayList<Integer>() {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
	private static int[] _validKeys = {11,   2,   3,   4,   5,   6,   7,   8,   9,  10,  30,  48,  46,  32,  18,  33,  34,  35,  23,  36,  37,  38,  50,  49,  24,  25,  16,  19,  31,  20,  22,  47,  17,  45,  21,  44,  146, 57,  147, 52,  51,  78,  53,  74};
	private static String[] _validKeyList = new String[200]; //= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", ":", " ", "_", ".", ",", "+", "-", "-"};

	public static void populateKeyList()
	{
		//_validKeys = {11,   2,   3,   4,   5,   6,   7,   8,   9,  10,  30,  48,  46,  32,  18,  33,  34,  35,  23,  36,  37,  38,  50,  49,  24,  25,  16,  19,  31,  20,  22,  47,  17,  45,  21,  44,  146, 57,  147, 52,  51,  78,  53,  74};
		_validKeyList[11] = "0";
		_validKeyList[2] = "1";
		_validKeyList[3] = "2";
		_validKeyList[4] = "3";
		_validKeyList[5] = "4";
		_validKeyList[6] = "5";
		_validKeyList[7] = "6";
		_validKeyList[8] = "7";
		_validKeyList[9] = "8";
		_validKeyList[10] = "9";
		_validKeyList[30] = "A";
		_validKeyList[48] = "B";
		_validKeyList[46] = "C";
		_validKeyList[32] = "D";
		_validKeyList[18] = "E";
		_validKeyList[33] = "F";
		_validKeyList[34] = "G";
		_validKeyList[35] = "H";
		_validKeyList[23] = "I";
		_validKeyList[36] = "J";
		_validKeyList[37] = "K";
		_validKeyList[38] = "L";
		_validKeyList[50] = "M";
		_validKeyList[49] = "N";
		_validKeyList[24] = "O";
		_validKeyList[25] = "P";
		_validKeyList[16] = "Q";
		_validKeyList[19] = "R";
		_validKeyList[31] = "S";
		_validKeyList[20] = "T";
		_validKeyList[22] = "U";
		_validKeyList[47] = "V";
		_validKeyList[17] = "W";
		_validKeyList[45] = "X";
		_validKeyList[21] = "Y";
		_validKeyList[44] = "Z";
		_validKeyList[146] = ":";
		_validKeyList[57] = " ";
		_validKeyList[147] = "_";
		_validKeyList[52] = ".";
		_validKeyList[51] = ",";
		_validKeyList[78] = "+";
		_validKeyList[53] = "-";
		_validKeyList[74] = "-";
	}

	public static String checkInput()
	{
		String asd = "";
		for(int i : _validKeys)
		{
			//System.out.println("Runnin: " + i);
			if(Keyboard.isKeyDown(i))
			{
				if(_pressedKey[i] == false)
				{
					_pressedKey[i] = true;
					asd += _validKeyList[i];
				}
			}
			else if(!Keyboard.isKeyDown(i) && _pressedKey[i] == true)
			{
				_pressedKey[i] = false;
			}
		}
		return asd;
	}
}
