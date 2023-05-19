import javax.swing.*;

public class ComponentArrayExample {
    public static void main(String[] args) {
        JComponent[] components = new JComponent[3];

        components[0] = new JButton("Click me");
        components[1] = new JSlider();
        components[2] =  new JCheckBox("Check me");

//        // Accessing the components in the array
//        for (JComponent component : components) {
//            if (component instanceof JButton) {
//                JButton btn = (JButton) component;
//                System.out.println("Button: " + btn.getText());
//            } else if (component instanceof JSlider) {
//                JSlider sldr = (JSlider) component;
//                System.out.println("Slider: min=" + sldr.getMinimum() + ", max=" + sldr.getMaximum());
//            } else if (component instanceof JCheckBox) {
//                JCheckBox cb = (JCheckBox) component;
//                System.out.println("Checkbox: " + cb.getText() + ", selected=" + cb.isSelected());
//            }
//        }
    }
}
