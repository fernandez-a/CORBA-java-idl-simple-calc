
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import CalcApp.*;
import CalcApp.CalcPackage.DivisionByZero;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import static java.lang.System.out;

public class CalcClient {

    static Calc calcImpl;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) {

        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "Calc";
            calcImpl = CalcHelper.narrow(ncRef.resolve_str(name));

            while (true) {
                out.println("1. Sum");
                out.println("2. Sub");
                out.println("3. Mul");
                out.println("4. Div");
                out.println("5. Square");
                out.println("6. Cube");
                out.println("7. Power");
                out.println("8. sin");
                out.println("9. cos");
                out.println("10. tan");
                out.println("11. log");
                out.println("12. ln");
                out.println("13. sqrt");
                out.println("14. exit");
                out.println("--");
                out.println("choice: ");

                try {
                    String opt = br.readLine();
                    if (opt.equals("14")) {
                        break;
                    } else if (opt.equals("1")) {
                        out.println("a+b= " + calcImpl.sum(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("2")) {
                        out.println("a-b= " + calcImpl.sub(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("3")) {
                        out.println("a*b= " + calcImpl.mul(getFloat("a"), getFloat("b")));
                    } else if (opt.equals("4")) {
                        try {
                            out.println("a/b= " + calcImpl.div(getFloat("a"), getFloat("b")));
                        } catch (DivisionByZero de) {
                            out.println("Division by zero!!!");
                        }
                    }
                    else if (opt.equals("5")) {
                        out.println("a^2= " + calcImpl.square(getDouble("a")));
                    }
                    else if (opt.equals("6")) {
                        out.println("a^3= " + calcImpl.cube(getDouble("a")));
                    }
                    else if (opt.equals("7")) {
                        out.println("a^b= " + calcImpl.power(getDouble("a"), getDouble("b")));
                    }
                    else if (opt.equals("8")) {
                        out.println("sin(a)= " + calcImpl.sin(getDouble("a")));
                    } else if (opt.equals("9")) {
                        out.println("cos(a)= " + calcImpl.cos(getDouble("a")));
                    } else if (opt.equals("10")) {
                        out.println("tan(a)= " + calcImpl.tan(getDouble("a")));
                    } else if (opt.equals("11")) {
                        out.println("log(a)= " + calcImpl.log(getDouble("a")));
                    } else if (opt.equals("12")) {
                        out.println("ln(a)= " + calcImpl.log(getDouble("a")));
                    }
                    else if (opt.equals("13")) {
                        out.println("sqrt(a)= " + calcImpl.sqrt(getDouble("a")));
                    }

                    
                } catch (Exception e) {
                    out.println("===");
                    out.println("Error with numbers");
                    out.println("===");
                }
                out.println("");

            }
            //calcImpl.shutdown();
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    static float getFloat(String number) throws Exception {
        out.print(number + ": ");
        return Float.parseFloat(br.readLine());
    }
    static double getDouble(String number) throws Exception {
    out.print(number + ": ");
    return Double.parseDouble(br.readLine());
    }
}
