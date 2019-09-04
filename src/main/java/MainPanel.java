import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainPanel extends JFrame {
    private JButton executar, sair;
    private ResolverController infController;
    private List<JRadioButton> funcionalidades;

    private MainPanel instante;

    public MainPanel() {
        super("Recomendação de Smartphones");

        this.infController = new ResolverController();
        this.instante = this;
        MainMenuHandler handler = new MainMenuHandler();

        this.funcionalidades = new ArrayList<>();

        executar = new JButton("Executar");
        executar.addActionListener(handler);

        sair = new JButton("Sair");
        sair.addActionListener(handler);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BoxLayout(painelPrincipal, BoxLayout.Y_AXIS));

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.X_AXIS));

        JPanel painelOpcoes = new JPanel();
        painelOpcoes.setLayout(new BoxLayout(painelOpcoes, BoxLayout.Y_AXIS));

        for (String funcName : infController.getFuncionalidades()) {
            funcionalidades.add(new JRadioButton(funcName));
        }

        for (JRadioButton jRadioButton : funcionalidades) {
            painelOpcoes.add(jRadioButton);
        }

        JScrollPane barraRolagem = new JScrollPane(painelOpcoes);

        painelBotoes.add(executar);
        painelBotoes.add(sair);

        painelPrincipal.add(painelOpcoes);
        painelPrincipal.add(painelBotoes);
        
        add(painelPrincipal);

        setLayout(new FlowLayout());
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setVisible(true);
    }

    private class MainMenuHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == executar) {
                List<String> funcSelecionadas = new ArrayList<>();

                for (JRadioButton func : funcionalidades) {
                    if (func.isSelected()) {
                        funcSelecionadas.add(func.getText());
                    }
                }

                if (e.getSource() == executar) {

                    if (!funcSelecionadas.isEmpty()) {

                        Resolver resolver = new Resolver(infController.smartphoneRules(), funcSelecionadas);
                        List<String> forwardResult = resolver.forwardResult();

                        List<String> func = new ArrayList<>();

                        for (JRadioButton funcionalidade : funcionalidades) {
                            func.add(funcionalidade.getText());
                        }

                        if (!func.isEmpty()) {
                            for (String cell : forwardResult) {
                                if (!func.contains(cell)) {
                                    System.out.println(cell);
                                    JOptionPane.showMessageDialog(null, cell);
                                }
                            }
                        } else {
                            for (String cell : forwardResult) {
                                System.out.println(cell);
                                JOptionPane.showMessageDialog(null, cell);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione ao menos uma opção", "Erro",
                                TrayIcon.MessageType.ERROR.ordinal());
                    }
                }
            }
            if (e.getSource() == sair) {
                dispose();
            }
        }

    }
}
