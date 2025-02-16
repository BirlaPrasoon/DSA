package designPatterns;

interface Monitor{
    void assemble();
}

class MsiMonitor implements Monitor {

    @Override
    public void assemble() {
        System.out.println("Msi Monitor assembled!!");
    }
}

class AsusMonitor implements Monitor {

    @Override
    public void assemble() {
        System.out.println("Asus Monitor assembled!!");
    }
}

interface Gpu{
    void assemble();
}

class MsiGpu implements Gpu{

    @Override
    public void assemble() {
        System.out.println("Msi GPU assembled!!");
    }
}

class AsusGpu implements Gpu {

    @Override
    public void assemble() {
        System.out.println("Asus GPU assembled!!");
    }
}

abstract class Company {
    public abstract Gpu createGpu();
    public abstract Monitor createMonitor();
}

class MsiManufacturer extends Company {

    @Override
    public Gpu createGpu() {
        return new MsiGpu();
    }

    @Override
    public Monitor createMonitor() {
        return new MsiMonitor();
    }
}

class AsusManufacturer extends Company {

    @Override
    public Gpu createGpu() {
        return new AsusGpu();
    }

    @Override
    public Monitor createMonitor() {
        return new AsusMonitor();
    }
}



public class AbstractFactory {

    public static void main(String[] args) {
        Company msi = new MsiManufacturer();
        Company asus = new AsusManufacturer();
        Gpu msiGpu = msi.createGpu();
        Monitor monitor = msi.createMonitor();
        Gpu asusGpu = asus.createGpu();
        Monitor asusMonitor = asus.createMonitor();
    }
}
