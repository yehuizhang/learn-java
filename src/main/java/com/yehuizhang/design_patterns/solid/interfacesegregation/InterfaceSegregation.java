package com.yehuizhang.design_patterns.solid.interfacesegregation;

/**
 * Interface Segregation Principle (ISP)
 * Many client-specific interfaces are better than one general-purpose interface.
 * Break large interface to small chucks.
 */

interface Document {
}

/*
This violates ISP
 */
interface Machine {
    void print(Document d);

    void fax(Document d);

    void scan(Document d);
}

/*
This violates ISP
 */
class MultiFunctionPrinter implements Machine {
    public void print(Document d) {
        // Implementation details
    }

    public void fax(Document d) {
        // Implementation details
    }

    public void scan(Document d) {
        // Implementation details
    }
}

/*
This violates ISP
 */
class OldFashionedPrinter implements Machine {
    public void print(Document d) {
        // Implementation details
    }

    public void fax(Document d) {
        // Fax is not supported
        // Unable to through Exception without updating the interface
    }

    public void scan(Document d) {
        // scan is not supported
        // Unable to through Exception without updating the interface
    }
}


/*
Proper Interface defined
 */
interface Printer {
    void print(Document d);
}
interface Scanner {
    void scan(Document d);
}
interface FaxMachine {
    void fax(Document d);
}

class TwoInOnePrinter implements Printer, Scanner {
    @Override
    public void print(Document d) {
    }

    @Override
    public void scan(Document d) {
    }
}

class HpFaxPrinter implements FaxMachine, Printer {
    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }
}


public class InterfaceSegregation {
}
