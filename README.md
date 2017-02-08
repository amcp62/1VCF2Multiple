# 1VCF2Multiple
Utility program to spread one file with several VCF cards into multiple VCF files.

To run the project from the command line, go to the dist folder and type:

    java -jar "1VCF2Multiple.jar" <inFilename> [outFilePrefix] [-t]

    @note: 'inFilename' is the file with multiple VCF cards
    @note: 'outFilePrefix' is the prefix appended to each resultant VCF card (ex: outFilePrefix1.vcf, outFilePrefix2.vcf...)
    @note: if ?outFilePrefix' is ommitted it is default to 'vcfContact_'
    @note: if option '-t' is active, only contacts with telephone number are converted.

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.
