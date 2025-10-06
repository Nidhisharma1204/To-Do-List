import java.sql.*;
import java.util.*;
public class jdbc1 {

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        //establish connection
       // Class.forName("org.postgresql.Driver");
        //String url="jdbc:postgresql://localhost:5432/JDBCdemo";
       // String query="insert into student values(10,'ed','ef')" ;
        //user writing there details
        //String name="nishi";
        //int id=7;
        //String subject="java";
        //String query="insert into student values(?,?,?)";
        //Connection con= DriverManager.getConnection(url,"postgres","0000");
        //System.out.println("connection establish");
        //Statement st =con.createStatement();
       // ResultSet rs=st.executeQuery(query);
        //System.out.println(st.execute(query));
//PreparedStatement st=con.prepareStatement(query);
//st.setString(2,name);
//st.setInt(1,id);
//st.setString(3,subject);
      //  st.execute();
//System.out.println();
       //while(rs.next()) {

           //String name = rs.getString("name");
           //String sub = rs.getString("subject");
           //Integer id = rs.getInt("id");
           //System.out.print(name + " ");
           //System.out.print(sub + " ");
           //System.out.print(id + " ");
           //System.out.println( " ");
       //}

       // con.close();
       // System.out.println("connection closed");

    //  Connection con=DriverManager.getConnection(url,"postgres","0000") ;
        private static  void addTask(String title){
            String url="jdbc:postgresql://localhost:5432/todo_app";
            String sql = "INSERT INTO tasks(title) VALUES(?)";
            try (Connection con=DriverManager.getConnection(url,"postgres","0000");
                    PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1, title);
                pst.executeUpdate();
                System.out.println("✅ Task Added!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        private static void viewTasks( ) {
            String url="jdbc:postgresql://localhost:5432/todo_app";
            String sql = "select * from tasks";
            try (Connection con=DriverManager.getConnection(url,"postgres","0000");
                 Statement st = con.createStatement();ResultSet rs = st.executeQuery(sql)) {
                System.out.println("\n📋 Your Tasks:");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + ". " + rs.getString("title") +
                            " [" + rs.getString("status") + "]");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private static void markAsDone(int id) {
            String url="jdbc:postgresql://localhost:5432/todo_app";
            String sql = "UPDATE tasks SET status='Done' WHERE id=?";
            try (Connection con=DriverManager.getConnection(url,"postgres","0000");
                 PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, id);
                int rows = pst.executeUpdate();
                if (rows > 0)
                    System.out.println("✅ Task Marked as Done!");
                else
                    System.out.println("⚠️ Task ID not found.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private static void deleteTask(int id) {
            String url="jdbc:postgresql://localhost:5432/todo_app";
            String sql = "DELETE FROM tasks WHERE id=?";
            try (Connection con=DriverManager.getConnection(url,"postgres","0000");
                 PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setInt(1, id);
                int rows = pst.executeUpdate();
                if (rows > 0)
                    System.out.println("🗑️ Task Deleted!");
                else
                    System.out.println("⚠️ Task ID not found.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) throws  Exception{
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- TO-DO LIST MENU ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Mark as Done");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter Task: ");
                    String title = sc.nextLine();
                    addTask(title);
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    System.out.print("Enter Task ID: ");
                    int idDone = sc.nextInt();
                    markAsDone(idDone);
                    break;
                case 4:
                    System.out.print("Enter Task ID: ");
                    int idDel = sc.nextInt();
                    deleteTask(idDel);
                    break;
                case 5:
                    System.out.println("👋 Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid Choice, try again.");
            }
        }
    }
}















