
import com.georges.menu.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OrderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    Order order = new Order();

    @Test
    public void Given_ChikenWithFriesAndWaterInStandardInput_When_MenuIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("1\n2\n3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Poulet", output[5]);
        assertEquals("Vous avez choisi comme Accompagnement : Frites", output[11]);
        assertEquals("Vous avez choisi comme Boisson : Soda", output[17]);
    }
    @Test
    public void Given_BeefWithVegetableInStandardInput_When_MenuIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("2\n1\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Boeuf", output[5]);
        assertEquals("Vous avez choisi comme Accompagnement : Legumes frais", output[11]);
    }
    @Test
    public void Given_VegetarianWithNoRiceAndSparklingWaterInStandardInput_When_MenuIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("3\n2\n2\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Vegetarian", output[5]);
        assertEquals("Vous avez choisi comme Accompagnement : Pas de riz", output[10]);
        assertEquals("Vous avez choisi comme Boisson : Eau gazeuse", output[16]);
    }

    @Test
    public void Given_OneMenuChikenWithFriesAndWaterInStandardInput_When_MenusIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("1\n1\n2\n3\n".getBytes()));
        order = new Order();
        order.runmenus();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Poulet", output[6]);
        assertEquals("Vous avez choisi comme Accompagnement : Frites", output[12]);
        assertEquals("Vous avez choisi comme Boisson : Soda", output[18]);
    }
    @Test
    public void Given_TwoMenu_BeefWithVegetable_VegetarianWithNoRiceAndSparklingWaterInStandardInput_When_MenusIsRun_Then_DisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("2\n2\n1\n3\n2\n2\n".getBytes()));
        order = new Order();
        order.runmenus();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Boeuf", output[6]);
        assertEquals("Vous avez choisi comme Accompagnement : Legumes frais", output[12]);
        assertEquals("Vous avez choisi comme Menu : Vegetarian", output[18]);
        assertEquals("Vous avez choisi comme Accompagnement : Pas de riz", output[23]);
        assertEquals("Vous avez choisi comme Boisson : Eau gazeuse", output[29]);
    }

    @Test
    public void Given_BadMenu_When_MenuIsRun_Then_ReAskMenu() {
        System.setIn(new ByteArrayInputStream("4\n1\n2\n3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de Menu parmi les choix proposés", output[5]);
        assertEquals("Vous avez choisi comme Menu : Poulet", output[7]);
    }

    @Test
    public void Given_ChikenWithBadSideAndBadDrink_When_MenuIsRun_Then_ReAskSideAndDrink() {
        System.setIn(new ByteArrayInputStream("1\n4\n2\n-1\n3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Poulet", output[5]);
        assertEquals("Vous n'avez pas choisi d'Accompagnement parmi les choix proposés", output[11]);
        assertEquals("Vous avez choisi comme Accompagnement : Frites", output[13]);
        assertEquals("Vous n'avez pas choisi de Boisson parmi les choix proposés", output[19]);
        assertEquals("Vous avez choisi comme Boisson : Soda", output[21]);
    }
    @Test
    public void Given_BeefWithBadSide_When_MenuIsRun_Then_ReAskSideAndDrink() {
        System.setIn(new ByteArrayInputStream("2\n4\n2\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Boeuf", output[5]);
        assertEquals("Vous n'avez pas choisi d'Accompagnement parmi les choix proposés", output[11]);
        assertEquals("Vous avez choisi comme Accompagnement : Frites", output[13]);
    }
    @Test
    public void Given_VegetarianWithBadSideAndBadDrink_When_MenuIsRun_Then_ReAskSideAndDrink() {
        System.setIn(new ByteArrayInputStream("3\n3\n2\n-1\n3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Vegetarian", output[5]);
        assertEquals("Vous n'avez pas choisi d'Accompagnement parmi les choix proposés", output[10]);
        assertEquals("Vous avez choisi comme Accompagnement : Pas de riz", output[12]);
        assertEquals("Vous n'avez pas choisi de Boisson parmi les choix proposés", output[18]);
        assertEquals("Vous avez choisi comme Boisson : Soda", output[20]);
    }

    @Test
    public void Given_BadResponseAndResponse1_When_AskAboutCarWithThreeResponses_Then_DisplayErrorAndGoodResponse() {
        System.setIn(new ByteArrayInputStream("5\n1\n".getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        order.askSomething("voiture", responses);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals(true, output[3].contains("voiture"));
        assertEquals("Vous n'avez pas choisi de voiture parmi les choix proposés", output[4]);
        assertEquals("Vous avez choisi comme voiture : BMW", output[6]);
    }
    @Test
    public void Given_Chiken_When_AskAboutMenus_Then_DisplayChikenChoice() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        order.askMenu();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Menu : Poulet", output[5]);
    }
    @Test
    public void Given_FriesWithAllSidesEnabled_When_AskAboutSides_Then_DisplayFriesChoice() {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        order = new Order();
        order.askSide(true);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Accompagnement : Frites", output[5]);
    }
    @Test
    public void Given_Water_When_AskAboutDrinks_Then_DisplayWaterChoice() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        order.askDrink();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous avez choisi comme Boisson : Eau fraiche", output[5]);
    }

    @Test
    public void Given_Response2_When_AskAboutCarWithThreeResponses_Then_ReturnNumber2() {
        System.setIn(new ByteArrayInputStream("5\n2\n".getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        int choice = order.askSomething("voiture", responses);
        assertEquals(2, choice);
    }
    @Test
    public void Given_Chiken_When_AskAboutMenus_Then_Return1() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        order = new Order();
        int choice = order.askMenu();
        assertEquals(1, choice);
    }
    @Test
    public void Given_Response_When_CallingAskQuestion_Then_FillOrderSummaryCorrectly() {
        System.setIn(new ByteArrayInputStream(String.format("1\n").getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        order.askSomething("voiture", responses);
        assertEquals(" Vous avez choisi comme voiture : BMW%n", order.orderSummary);
    }
    @Test
    public void Given_Responses_When_CallingRunMenus_Then_FillOrderSummaryCorrectly() {
        System.setIn(new ByteArrayInputStream(String.format("2%n1%n1%n1%n2%n2%n").getBytes()));
        order = new Order();
        order.runmenus();
        assertEquals("Resume de votre commande : %n" +
                "%nMenu 1 : %n" +
                "Vous avez choisi comme Menu : Poulet%n" +
                "Vous avez choisi comme Accompagnement : Legumes frais%n" +
                "Vous avez choisi comme Boisson : Eau fraiche%n" +
                "%nMenu 2 : %n" +
                "Vous avez choisi comme Menu : Boeuf%n" +
                "Vous avez choisi comme Accompagnement : Frites%n" , order.orderSummary);
    }
    @Test
    public void Given_TextResponse_When_CallingAskQuestion_Then_DisplayErrorSentence() {
        System.setIn(new ByteArrayInputStream(String.format("texte%n1%n").getBytes()));
        order = new Order();
        String[] responses = {"BMW", "Audi", "Mercedes"};
        order.askSomething("voiture", responses);
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas choisi de voiture parmi les choix proposés", output[4]);
    }
    @Test
    public void Given_BadMenusQuantityInStandardInput_When_MenusIsRun_Then_DisplayErrorSentence() {
        System.setIn(new ByteArrayInputStream(String.format("texte%n1%n1%n2%n3%n").getBytes()));
        order = new Order();
        order.runmenus();
        String[] output = outContent.toString().replace("\r\n", "\n").split("\n");
        assertEquals("Vous n'avez pas entrez une bonne valeur. ", output[1]);
    }
}