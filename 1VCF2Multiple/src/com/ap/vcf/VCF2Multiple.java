/*
 * Copyright (C) 2015 Antonio Pereira
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ap.vcf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author <a href="mailto:amcp.62@gmail.com">Ant&oacute;nio Pereira</a>
 */
public class VCF2Multiple {

    private String inFilename = null;
    private String outPrefixname = "vcfContact_";
    private boolean onlyTel = false;

    public VCF2Multiple(String[] args) {
        inFilename = args[0];
        if (args.length > 1) {
            int i = 1;
            while (args.length > i) {
                if (args[i].equalsIgnoreCase("-t")) {
                    onlyTel = true;
                } else {
                    outPrefixname = args[i];
                }
                i++;
            }
        }
    }

    public void expandContacts() {
        // This will reference one line at a time
        String line = null;
        // This will reference one contact at a time
        StringBuilder contact = new StringBuilder();
        //This will reference the contact number
        int n = 0;

        // Always wrap FileReader in BufferedReader.
        // FileReader reads text files in the default encoding.
        try (FileReader fileReader = new FileReader(inFilename); 
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            boolean valid = !onlyTel;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equalsIgnoreCase("BEGIN:VCARD")) {
                    contact.delete(0, contact.length());
                }
                if (line.startsWith("TEL;")) {
                    valid = true;
                }
                contact.append(line);
                contact.append("\r\n");
                if (line.equalsIgnoreCase("END:VCARD")) {
                    if (valid) {
                        writeContactInFile(outPrefixname, n, contact);
                        n++;
                    }
                    valid = !onlyTel;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage() + " '"
                    + inFilename + "'");
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + " '"
                    + inFilename + "'");
        }

    }

    private void writeContactInFile(String prefix, int number,
            StringBuilder contact) {
        FileOutputStream outputStream = null;
        String outFile = prefix + number + ".vcf";
        try {
            outputStream = new FileOutputStream(outFile);
            String info = contact.toString();
            outputStream.write(info.getBytes());
            // Always close files.
            outputStream.close();
        } catch (FileNotFoundException ex) {
            System.err.println(
                    ex.getMessage() + " '" + outFile + "'");
        } catch (IOException ex) {
            System.err.println(
                    ex.getMessage() + " '" + outFile + "'");
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.err.println(
                            ex.getMessage() + " '" + outFile + "'");
                }
            }
        }
    }

}
