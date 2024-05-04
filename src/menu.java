import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class menu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private jdbc jdbc_Obj = new jdbc();
	private security security_Obj = new security();
	
	private int numcheck = 0;
	
	private JPanel contentPane;
	private JTextField tf_Username;
	private JPasswordField pwf_Userpass;
	private JTextField tf_Username_1;
	private JPasswordField pwf_Userpass_1;
	private JPasswordField pwf_Userpass_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menu frame = new menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	private int check1() {
		
		// neu nhu tentaikhoan, mat khau de trong
		if (tf_Username.getText().isEmpty() || new String(pwf_Userpass.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tên tài khoản hay mật khẩu không thể để trống", "Error", JOptionPane.PLAIN_MESSAGE);
			return 0;
		}
		
		// neu nhu tentaikhoan khong ton tai
		String str1 = "SELECT Username FROM tk WHERE Username = ?";
		if (!jdbc_Obj.select(str1, tf_Username.getText(), "Username")) {
			JOptionPane.showMessageDialog(null, "Tên tài khoản này không tồn tại!", "Error", JOptionPane.PLAIN_MESSAGE);
			return 0;
		}
		
		// neu nhu mat khau ma hoa Md5 khong khop voi trong database
		String mkcheck = security_Obj.getMd5(new String(pwf_Userpass.getPassword()));
		str1 = "SELECT Username, Userpass FROM tk WHERE Username = ?";
		if (!jdbc_Obj.select2(str1, tf_Username.getText(), mkcheck)) {
			JOptionPane.showMessageDialog(null, "Sai mật khẩu!", "Error", JOptionPane.PLAIN_MESSAGE);
			return 0;
		}
		

		return 1;
	}
	
	
	private int check2() {
		
		// neu nhu tentaikhoan, mat khau, xac nhan mat khau de trong
		if (tf_Username_1.getText().isEmpty() || new String(pwf_Userpass_1.getPassword()).isEmpty() || new String(pwf_Userpass_2.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tên tài khoản hay mật khẩu, xác nhận mật khẩu không thể để trống", "Error", JOptionPane.PLAIN_MESSAGE);
			return 0;
		}
		
		// neu nhu xac nhan mat khau khong trung voi mat khau
		if (!new String(pwf_Userpass_1.getPassword()).equals(new String(pwf_Userpass_2.getPassword()))) {
			JOptionPane.showMessageDialog(null, "Xác nhận mật khẩu không khớp", "Error", JOptionPane.PLAIN_MESSAGE);
			return 0;
		}
		
		// neu nhu ten tai khoan bi trung lap
		String str1 = "SELECT Username FROM tk WHERE Username = ?";
		if (jdbc_Obj.select(str1, tf_Username_1.getText(), "Username")) {
			JOptionPane.showMessageDialog(null, "Tên tài khoản này đã tồn tại!", "Error", JOptionPane.PLAIN_MESSAGE);
			return 0;
		}
		
		
		// them vao database
		String mk = security_Obj.getMd5(new String(pwf_Userpass_1.getPassword()));
		jdbc_Obj.update(tf_Username_1.getText(), mk);
		JOptionPane.showMessageDialog(null, "Đăng ký tài khoản thành công", "Information", JOptionPane.PLAIN_MESSAGE);
		return 1;
	}

	
    private void show1(JFrame parentFrame) {
        JDialog dialog = new JDialog(parentFrame, "Đăng nhập/Đăng ký", true);


		JTabbedPane Tabpad1 = new JTabbedPane(JTabbedPane.TOP);
		Tabpad1.setBounds(0, 0, 600, 450);
		
		JPanel pad1 = new JPanel();
		Tabpad1.addTab("Đăng nhập", null, pad1, null);
		pad1.setLayout(null);
		
		JLabel lb_Dangnhap = new JLabel("Đăng nhập");
		lb_Dangnhap.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lb_Dangnhap.setBounds(23, 10, 212, 63);
		pad1.add(lb_Dangnhap);
		
		tf_Username = new JTextField();
		tf_Username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_Username.setBounds(204, 143, 381, 39);
		pad1.add(tf_Username);
		tf_Username.setColumns(10);
		
		JLabel lb_Username = new JLabel("Tên tài khoản");
		lb_Username.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Username.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_Username.setBounds(10, 143, 170, 39);
		pad1.add(lb_Username);
		
		JButton bt_Login = new JButton("Login");
		bt_Login.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bt_Login.setBounds(225, 368, 141, 45);
		bt_Login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check1() == 1) {
					dialog.dispose();
					numcheck = 1;
				}
				
			}
		});
		pad1.add(bt_Login);
		
		JLabel lb_Userpass = new JLabel("Mật khẩu");
		lb_Userpass.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Userpass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_Userpass.setBounds(10, 208, 170, 39);
		pad1.add(lb_Userpass);
		
		pwf_Userpass = new JPasswordField();
		pwf_Userpass.setBounds(204, 208, 381, 39);
		pad1.add(pwf_Userpass);
		
		JPanel pad2 = new JPanel();
		Tabpad1.addTab("Đăng kí", null, pad2, null);
		pad2.setLayout(null);
		
		JLabel lb_Dangnhap_1 = new JLabel("Đăng nhập");
		lb_Dangnhap_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lb_Dangnhap_1.setBounds(23, 10, 212, 63);
		pad2.add(lb_Dangnhap_1);
		
		tf_Username_1 = new JTextField();
		tf_Username_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_Username_1.setColumns(10);
		tf_Username_1.setBounds(204, 143, 381, 39);
		pad2.add(tf_Username_1);
		
		JLabel lb_Username_1 = new JLabel("Tên tài khoản");
		lb_Username_1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Username_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_Username_1.setBounds(10, 143, 170, 39);
		pad2.add(lb_Username_1);
		
		JButton bt_Signin = new JButton("Sign in");
		bt_Signin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bt_Signin.setBounds(225, 368, 141, 45);
		bt_Signin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (check2() == 1) {
					dialog.dispose();
					numcheck = 1;
				}
				
			}
		});
		pad2.add(bt_Signin);
		
		JLabel lb_Userpass_1 = new JLabel("Mật khẩu");
		lb_Userpass_1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Userpass_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_Userpass_1.setBounds(10, 208, 170, 39);
		pad2.add(lb_Userpass_1);
		
		pwf_Userpass_1 = new JPasswordField();
		pwf_Userpass_1.setBounds(204, 208, 381, 39);
		pad2.add(pwf_Userpass_1);
		
		JLabel lb_Userpass_2 = new JLabel("Xác nhận mật khẩu");
		lb_Userpass_2.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Userpass_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lb_Userpass_2.setBounds(10, 267, 170, 39);
		pad2.add(lb_Userpass_2);
		
		pwf_Userpass_2 = new JPasswordField();
		pwf_Userpass_2.setBounds(204, 267, 381, 39);
		pad2.add(pwf_Userpass_2);

        dialog.getContentPane().add(Tabpad1);

        // Set dialog properties
        dialog.setSize(650, 500);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }
	
	
	public menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(282, 228, 652, 120);
		contentPane.add(lblNewLabel);
		
		// Dialog
		JFrame DiaFrame = new JFrame("JDialog's frame");
		show1(DiaFrame);
		if (numcheck == 0) {
			System.exit(0);
		}
	}

}
