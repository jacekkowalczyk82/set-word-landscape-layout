/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.jacekkowalczyk82.docx.tools;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;



public class LandscapeLayout {


    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
        }
        else {
            System.out.println("Setting Landscape page layout");

            try {
                String inputFileName = args[0];

                XWPFDocument document= new XWPFDocument(new FileInputStream(inputFileName));

                // Set page layout to landscape
                CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
                XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);
                CTPageSz pageSize = sectPr.addNewPgSz();
                pageSize.setOrient(STPageOrientation.LANDSCAPE);
                pageSize.setW(BigInteger.valueOf(16840)); // 11.69 inch (A4 landscape width)
                pageSize.setH(BigInteger.valueOf(11900)); // 8.27 inch (A4 landscape height)




                FileOutputStream fileOut = new FileOutputStream(inputFileName+ "_landscape.docx");
                document.write(fileOut);
                fileOut.close();
                document.close();

                System.out.println("DONE");

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public static void usage() {
        System.out.println("Setting Landscape page layout");
        System.out.println("Required parameters were not provided");
        System.out.println("Usage: landscapelayout.bat <PATH TO INPUT DOCX FILE>");
    }
}
