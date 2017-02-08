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

/**
 * Main class - verify the mandatory arguments and invoke conversion class.
 * 
 * @author <a href="mailto:amcp.62@gmail.com">Antonio Pereira</a> 
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // The name of the file to open is mandatory
        if (args.length < 1) {
            System.out.println("\n   Application that converts one VCF file "
                    + "with multiple contacts");
            System.out.println("     into multiple VCF single contact files.");
            System.out.println("\n Usage:\n");
            System.out.println("  java -jar 1VCF2Multiple.jar <inFilename>"
                    + " [outFilePrefix] [-t]");
            System.out.println("\n  If outFilePrefix ommitted it is assumed"
                    + " \"vcfContact_\" by default.");
            System.out.println("\n  If option '-t' active, only contacts"
                    + " with telephone number are converted.\n");
            return;
        }

        VCF2Multiple vcf2m = new VCF2Multiple(args);
        vcf2m.expandContacts();
    }

}
