import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener {
JMenuBar menubar;
JMenu Details,Ticket;
JMenuItem FlightDetails,CustomerDetails,ReservationnDetails,BookFlightDetails,JourneyDetails,TicketCanceliation,BoardingPass;
    Home(){
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        // for image3
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("front.jpg"));
        Image i2=i1.getImage().getScaledInstance(1400,800,Image.SCALE_SMOOTH);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(0,0,1400,800);
        add(image);

        JLabel Heading=new JLabel("Welcome To Nepal Airliness");
        Heading.setBounds(500,60,550,30);

        Heading.setFont(new Font("Arial",Font.BOLD,30));
        image.add(Heading);

        // for first menu
         menubar=new JMenuBar();
        setJMenuBar(menubar);
         Details =new JMenu("Details");
        menubar.add(Details);

         FlightDetails =new JMenuItem("FlightDetails");
         FlightDetails.addActionListener(this);
         Details.add(FlightDetails);

         CustomerDetails=new JMenuItem("CustomerDetails");
         CustomerDetails.addActionListener(this);
         Details.add(CustomerDetails);

         BookFlightDetails=new JMenuItem("BookFlight");
         BookFlightDetails.addActionListener(this);
         Details.add(BookFlightDetails);

         JourneyDetails=new JMenuItem("JourneyDetails");
         JourneyDetails.addActionListener(this);
         Details.add(JourneyDetails);

         TicketCanceliation=new JMenuItem("TicketCanceliation");
         TicketCanceliation.addActionListener(this);
         Details.add(TicketCanceliation);




    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        if (text.equals("FlightDetails")) {
         new FlightINfo();
        } else if (text.equals("CustomerDetails")) {
            new AddCustomer();

        } else if (text.equals("BookFlight")) {

            new BookFlifht();

        } else if (text.equals("JourneyDetails")) {
            new JourneryDetails();

        } else if (text.equals("TicketCanceliation")) {
            new Cancel();

        }



    }

    public static void main(String[] args) {
        new Home();
    }
}
