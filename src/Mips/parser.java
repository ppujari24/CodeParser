package Mips;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class parser  {
	public parser(Scanner input) throws IOException {
		FileWriter file = new FileWriter("Output.txt");
		while (input.hasNext()) {
            String temp = input.nextLine();
            if(!temp.equals(null)){
            	String outputString = mipsToBinary(temp);
            	file.write(outputString);
            	file.write("\n");
            } 
		}
		file.close();
		input.close();
	}

	public String mipsToBinary(String instruction){
		StringBuffer b = new StringBuffer();
		
		String opcode,reg1,reg2,reg3,reg4,reg;
		String[] parts,regs;
		
		//System.out.println(instruction);
		
		parts = instruction.split(" ");
		opcode = parts[0];
		if(instruction.equalsIgnoreCase("lnop") || instruction.equalsIgnoreCase("nop")){
			switch(opcode){
			case "lnop":
				b.append("00000000001");
				reg1 = "0000000";
				reg2 = "0000000";
				reg3 = "0000000";
				b.append(reg3).append(reg2).append(reg1);
				
				break;
				
			case "nop":
				b.append("01000000001");
				reg1 = "0000000";
				reg2 = "0000000";
				reg3 = "0000000";
				b.append(reg3).append(reg2).append(reg1);
				
				break;
			}
			return b.toString();
		}


		reg = parts[1];
		
		switch(opcode){
		
			
		case "lqd":              //Load Quadword d-form
			b.append("00110100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());
			//reg3 = String.format("%03d", reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "lqx":
			b.append("00111000100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "lqa":
			b.append("001100001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000000000" + reg2).substring(reg2.length());
			b.append(reg2).append(reg1);
			
			break;
			
		case "stqd":			//Store Quadword d-form
			b.append("00100100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());
			//reg3 = String.format("%03d", reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "stqx": 
			b.append("00101000100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "stqa":
			b.append("001000001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000000000" + reg2).substring(reg2.length());
			b.append(reg2).append(reg1);
			
			break;
			
		case "ilh":
			b.append("010000011");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000000000" + reg2).substring(reg2.length());
			b.append(reg2).append(reg1);
			
			break;
			
		case "il":
			b.append("010000001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000000000" + reg2).substring(reg2.length());
			b.append(reg2).append(reg1);
			
			break;
			
		case "ila":
			b.append("0100001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("000000000000000000" + reg2).substring(reg2.length());
			b.append(reg2).append(reg1);
			
			break;
			
		case "heq":
			b.append("01111011000");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3="0000000";
			b.append(reg2).append(reg1).append(reg3);
			
			break;
			
		case "heqi":
			b.append("01111111");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000" + reg2).substring(reg2.length());
			reg3="0000000";
			b.append(reg2).append(reg1).append(reg3);
			
			break;
			
			
			
		case "br":
			b.append("001100100");
			regs = reg.split(",");
			reg2=regs[0];
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000000000" + reg2).substring(reg2.length());
			reg3 = "0000000";
			b.append(reg2).append(reg3);
			
			break;
			
			
		case "bra":
			b.append("001100000");
			regs = reg.split(",");
			reg2=regs[0];
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000000000" + reg2).substring(reg2.length());
			reg3 = "0000000";
			b.append(reg2).append(reg3);
			
			break;
			
			
		case "brnz":
			b.append("001000010");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000000000" + reg2).substring(reg2.length());
			b.append(reg2).append(reg1);
			
			break;
			
		case "brz":
			b.append("001000000");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=Integer.toBinaryString(Integer.parseInt(reg2));
			reg2=("0000000000000000" + reg2).substring(reg2.length());
			b.append(reg2).append(reg1);
			
			break;
			
			
		case "shlqbi":
			b.append("00111011011");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "shlqbyi":
			b.append("00111111111");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "rotqbi":
			b.append("00111011000");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "rotqbii":
			b.append("00111111000");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		//even instructions
			
		case "ah":
			b.append("00011001000");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "ahi":
			b.append("00011101");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "a":
			b.append("00011000000");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "ai":
			b.append("00011100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "sfh":
			b.append("00001001000");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "sfhi":
			b.append("00001101");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "sf":
			b.append("00001000000");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "sfi":
			b.append("00001100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "clz":
			b.append("01010100101");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3 = "0000000";
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "and":
			b.append("00011000001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "andc":
			b.append("01011000001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "andhi":
			b.append("00010101");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "andi":
			b.append("00010100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "or":
			b.append("00001000001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "orc":
			b.append("01011001001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
			
		case "orhi":
			b.append("00000101");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "ori":
			b.append("00000100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "xor":
			b.append("01001000001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "xorhi":
			b.append("01000101");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "xori":
			b.append("01000100");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
		
			
		case "nand":
			b.append("00011001001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
			
		case "nor":
			b.append("00001001001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "eqv":
			b.append("01001001001");
			regs = reg.split(",");
			reg1=regs[0];
			reg2=regs[1];
			reg3=regs[2];
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "selb":
			b.append("1000");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg4=regs[3];//rc
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			reg4=RegAddress(reg4);
			b.append(reg1).append(reg3).append(reg2).append(reg4);
			
			break;
			
			
		case "shlh":
			b.append("00001011111");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "shl":
			b.append("00001011011");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "roth":
			b.append("00001011100");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "rot":
			b.append("00001011000");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "ceqb":
			b.append("01111010000");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "ceqh":
			b.append("01111001000");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "cgtb":
			b.append("01001010000");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		case "cgth":
			b.append("01001001000");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "fm":
			b.append("01011000110");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "fs":
			b.append("01011000101");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "fa":
			b.append("01011000100");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "fma":
			b.append("1110");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg4=regs[3];//rc
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			reg4=RegAddress(reg4);
			b.append(reg1).append(reg3).append(reg2).append(reg4);
			
			break;
			
		case "mpy":
			b.append("01111000100");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "mpyi":
			b.append("01110100");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//value
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=Integer.toBinaryString(Integer.parseInt(reg3));
			reg3=("0000000000" + reg3).substring(reg3.length());			
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "mpya":
			b.append("1100");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg4=regs[3];//rc
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			reg4=RegAddress(reg4);
			b.append(reg1).append(reg3).append(reg2).append(reg4);
			
			break;
			
			
		case "cntb":
			b.append("01010110100");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3="0000000";
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
			
		case "avgb":
			b.append("00011010011");
			regs = reg.split(",");
			reg1=regs[0];//rt
			reg2=regs[1];//ra
			reg3=regs[2];//rb
			reg1=RegAddress(reg1);
			reg2=RegAddress(reg2);
			reg3=RegAddress(reg3);
			b.append(reg3).append(reg2).append(reg1);
			
			break;
			
		
			
			
			
		default : break;
		}
		
		return b.toString();
		
	}
	
	
	
	
	
	public String RegAddress(String reg){
		reg=reg.substring(1);
		reg=Integer.toBinaryString(Integer.parseInt(reg));
		reg=("0000000" + reg).substring(reg.length());
		return reg;
	}
	

}
