package factory;

import java.util.Scanner;
import model.ItemPedido;

public interface ItemFactory {
    ItemPedido criarItem(Scanner scanner);
}