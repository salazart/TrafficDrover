package com.trafficdrover.folder.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FilterStream extends FileInputStream{
	
	private int charCode;

	public FilterStream(String filename, int charCode) throws FileNotFoundException {
		super(filename);
		this.charCode = charCode;
	}
	
	@Override
	public int read() throws IOException {
		int chr = super.read();
		char q = (char) chr;
    	//System.out.println(chr + " " + q);
		
		if (/*chr == 0x23 || chr == 0x26 ||*/ chr == charCode /*|| chr == 38*/) {
			//System.out.println("OK");
			return read();
		} else {
			return chr;
		}
	}

    @Override
    public int read(byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int n = 0, c;
        do {
            c = this.read();
            if(c != -1) {
                b[off + n] = (byte) c;
                n++;
                len--;  
            } else {
                return c;
            }
        } while(c != -1 && len > 0);
        return n;
    }


//    @Override
//    public int read() throws IOException {
//        int c;
//        do {
//            c = super.read();
//        } while(c != -1 && (c == '\n' || c == '\r')/*&& (c == 17)*/);
//        return c;
//    }
	//ch != 0x23 && ch != 0x26 && ch != 17
}
