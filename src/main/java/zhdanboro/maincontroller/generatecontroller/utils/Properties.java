package zhdanboro.maincontroller.generatecontroller.utils;

public class Properties {
        String polynomial;
        double deviation;
        int sequenceLength;
        int generationCount;
        int startPosition;
        boolean saveToBase;
        boolean analyzeSequence;

        String processPolynomial;

    public String getPolynomial() {
        return polynomial;
    }
    public double getDeviation() {
        return deviation;
    }
    public int getSequenceLength() {
        return sequenceLength;
    }
    public int getGenerationCount() {
        return generationCount;
    }
    public int getStartPosition() {
        return startPosition;
    }
    public boolean isSaveToBase() {
        return saveToBase;
    }
    public boolean isAnalyzeSequence() {
        return analyzeSequence;
    }

    public String getProcessPolynomial() {
        return processPolynomial;
    }

    public void setPolynomial(String polynomial) {
        this.polynomial = polynomial;
    }
    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }
    public void setSequenceLength(int sequenceLength) {
        this.sequenceLength = sequenceLength;
    }
    public void setGenerationCount(int generationCount) {
        this.generationCount = generationCount;
    }
    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
    public void setSaveToBase(boolean saveToBase) {
        this.saveToBase = saveToBase;
    }
    public void setAnalyzeSequence(boolean analyzeSequence) {
        this.analyzeSequence = analyzeSequence;
    }

    public void setProcessPolynomial(String processPolynomial) {
        this.processPolynomial = processPolynomial;
    }
}
