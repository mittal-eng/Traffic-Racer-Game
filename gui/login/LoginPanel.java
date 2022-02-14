package gui.login;

import engine.Player;
import game.Current;
import gui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Leo on 04-Nov-15.
 */
public class LoginPanel extends JPanel
{
    JFrame windowReference;

    JLabel welcomeLabel;
    JLabel usernameLabel;
    JLabel passwordLabel;

    JTextField usernameTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();

    JButton loginButton;
    JButton registerButton;

    JPanel usernamePanel;
    JPanel passwordPanel;
    JPanel buttonsPanel;

    public LoginPanel(JFrame windowReference)
    {
        this.windowReference = windowReference;
        this.initializeButtons();
        this.initializeLabels();
        this.initializeSubPanels();
        this.initializeThisPanel();
    }

    private void initializeThisPanel()
    {
        this.setLayout(new GridLayout(6, 1, 0, 10));
        this.add(new JPanel());
        this.add(welcomeLabel);
        this.add(usernamePanel);
        this.add(passwordPanel);
        this.add(buttonsPanel);
        this.add(new JPanel());
    }

    private void initializeSubPanels()
    {
        usernamePanel = new JPanel();
        usernamePanel.setLayout(new GridLayout(1, 2, 0, 0));
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);

        passwordPanel = new JPanel();
        passwordPanel.setLayout(new GridLayout(1, 2, 0, 0));
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 4, 10, 0));
        buttonsPanel.add(new JPanel());
        buttonsPanel.add(loginButton);
        buttonsPanel.add(registerButton);
        buttonsPanel.add(new JPanel());
    }

    private void initializeLabels()
    {
        this.welcomeLabel = new JLabel("Welcome to Traffic Racer!");
        this.usernameLabel = new JLabel("Username: ");
        this.passwordLabel = new JLabel("Password: ");

        this.welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.welcomeLabel.setVerticalTextPosition(SwingConstants.CENTER);

        this.usernameLabel.setVerticalTextPosition(SwingConstants.CENTER);
        this.passwordLabel.setVerticalTextPosition(SwingConstants.CENTER);
    }

    private void initializeButtons()
    {
        this.loginButton = new JButton("Login");
        this.registerButton = new JButton("Register");

        this.loginButton.addActionListener(new LoginButtonListener());
        this.registerButton.addActionListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String claimedUsername = usernameTextField.getText().toLowerCase().trim();
            String claimedPassword = String.valueOf(passwordTextField.getPassword()).toLowerCase().trim();

            if (Player.isUser(claimedUsername))
            {
                if (Player.isValid(claimedUsername, claimedPassword))
                {
                    Current.player = Player.readFromFile(claimedUsername);
                }

                else
                {
                    JOptionPane.showMessageDialog(windowReference, "The password doesn't match!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            else
            {
                JOptionPane.showMessageDialog(windowReference, "The user doesn't exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            /* Create the game window */
            GameFrame window = new GameFrame();

            /* Kill login frame */
            windowReference.setVisible(false);
            windowReference.dispose();
        }
    }

    private class RegisterButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String claimedUsername = usernameTextField.getText().toLowerCase().trim();
            String claimedPassword = String.valueOf(passwordTextField.getPassword()).toLowerCase().trim();

            if (!Player.isUser(claimedUsername))
            {
                Player a = new Player(claimedUsername, claimedPassword);
                a.saveToFile();

                JOptionPane.showMessageDialog(windowReference, "Successfully Registered!", "Success", JOptionPane.PLAIN_MESSAGE);
            }

            else
            {
                JOptionPane.showMessageDialog(windowReference, "The username is already taken!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
