public class jType extends InstructionInfo{
    String machineCode;
    String mnemonic;
    String opcode;
    String index;
    // Declaring Variables for translating to hexadecimal
    int ans;
    String temp;

    public jType(){
        super();
    }

    public void setMachineCode(String machineCode){
        this.machineCode = machineCode;
        getOpcode();
        getIndex();// we will use address to get index.
        getMnemonic();
    }

    public void getOpcode(){
        this.opcode = machineCode.substring(0,6);
        //Binary to decimal Conversion
        temp = this.opcode;
        ans = Integer.parseInt(temp, 2);
        //Convert decimal to hex.
        opcode = Integer.toHexString(ans);
        if(opcode.length() == 1) {
            opcode = "0" + opcode;
        }
    }

    private void getIndex(){
        this.index = machineCode.substring(6,32); // get address
        StringBuilder result = new StringBuilder();
        //Binary to decimal Conversion
        temp = this.index;
        ans = Integer.parseInt(temp, 2);
        //Convert decimal to hex.
        index = Integer.toHexString(ans);
        for(int i = 0; i < 7 - index.length(); i++){
            result.append("0");
        }
        result.append(index);
        index = result.toString();
    }

    private void getMnemonic(){
        if(this.opcode.equals("02")){// set mnemonic to addiu
            this.mnemonic = "j";
        }
    }

    public String toString(){
        return this.mnemonic + " {opcode: " + opcode + ", index: " + index + "}";
    }
}
