# 1VCF2Multiple
Spread one file with several VCF cards to multiple VCF files

To run the project from the command line, go to the dist folder and type:

    java -jar "1VCF2Multiple.jar" <inFilename> [outFilePrefix] [-t]

      - 'inFilename' is the file that contains multiple VCF cards
      - 'outFilePrefix' is prefixed to each generated VCF card file (ex: outFilePrefix0.vcf, outFilePrefix1.vcf...)
      - if 'outFilePrefix' is ommitted 'vcfContact_' is used by default
      - if option '-t' is active, only contacts with telephone number are converted.

To distribute this project, zip up the dist folder (including the lib folder) and distribute the ZIP file.
