// Multiple Inheritance
/**
 * Imagine you are designing a system for smart home devices. You have different types of devices, such as:

Switchable: Devices that can be turned on or off.

Adjustable: Devices whose settings (for example, brightness and volume) can be adjusted.

Connectable: Devices that can connect to a network.

A smart bulb, for example, can be switchable, adjustable, and connectable. Whereas a dimmable bulb can be switchable and adjustable. A regular bulb is only switchable. To model this, you can use multiple interfaces.
 */

//  An interface is very similar to a class but cannot have any class attributes and can have only abstract methods.

//Interface for device that can be turned on/ off

interface Switchable {
    void turnOn();
    void turnOff();
}

// Interface for devices with adjustable settings

interface Adjustable {
    void increase();
    void decrease();
}

// Interface for device that can connect to a network

interface Connectable {
    void connect();
    void disconnect();
}

class SmartBulb implements Switchable, Adjustable, Connectable {
    private boolean isOn = false;
    private int brightness = 50; //default brightness
    private boolean isConnected = false;

    // Switchable Methods
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("Smart Bulb is turned on");
    }
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("Smart Bulb is turned off");
    }

    // Adjustable methods
    @Override
    public void increase() {
        if (brightness < 100) {
            brightness += 10;
            System.out.println("Brightness increased to " + brightness + "%.");
        } else {
            System.out.println("Brightness is already at maximum.");
        }
    }
    @Override
    public void decrease() {
        if (brightness > 0) {
            brightness -= 10;
            System.out.println("Brightness decreased to " + brightness + "%.");
        } else {
            System.out.println("Brightness is already at minimum.");
        }
    }
    // Connectable methods
    @Override
    public void connect() {
        isConnected = true;
        System.out.println("SmartBulb is connected to the network.");
    }
    @Override
    public void disconnect() {
        isConnected = false;
        System.out.println("SmartBulb is disconnected from the network.");
    }
}
// DimmableBulb class implementing two interfaces
class DimmableBulb implements Switchable, Adjustable {
    private boolean isOn = false;
    private int brightness = 50; // Default brightness level
    // Switchable methods
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("DimmableBulb is turned ON.");
    }
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("DimmableBulb is turned OFF.");
    }
    // Adjustable methods
    @Override
    public void increase() {
        if (brightness < 100) {
            brightness += 10;
            System.out.println("Brightness increased to " + brightness + "%.");
        } else {
            System.out.println("Brightness is already at maximum.");
        }
    }
    @Override
    public void decrease() {
        if (brightness > 0) {
            brightness -= 10;
            System.out.println("Brightness decreased to " + brightness + "%.");
        } else {
            System.out.println("Brightness is already at minimum.");
        }
    }
}
// RegularBulb class implementing one interface
class RegularBulb implements Switchable {
    private boolean isOn = false;
    // Switchable methods
    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("RegularBulb is turned ON.");
    }
    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("RegularBulb is turned OFF.");
    }
}

/**
 * Interfaces:
Switchable Defines methods for turning a device on or off.

Adjustable Defines methods for increasing or decreasing settings.

Connectable Defines methods for connecting or disconnecting a device.

SmartBulb Class:
Implements all three interfaces (Switchable, Adjustable, Connectable).

Dimmable Class:
Implements two interfaces (Switchable, Adjustable).
 */