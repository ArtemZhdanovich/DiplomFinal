package zhdanboro.controller.generatecontroller.utils;

public class Properties {
        String polynomial;
        int[] shifts;
        double deviation;
        int sequenceLength;
        int generationCount;
        int startPosition;
        boolean singleGraph;
        boolean saveToBase;
        boolean analyzeSequence;

        String processPolynomial;

    public String getPolynomial() {
        return polynomial;
    }
    public int[] getShifts() {return shifts;}
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
    public boolean isSingleGraph() {
        return singleGraph;
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
    public void setShifts(int[] shifts) {this.shifts = shifts;}
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
    public void setSingleGraph(boolean singleGraph) { this.singleGraph = singleGraph; }
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
