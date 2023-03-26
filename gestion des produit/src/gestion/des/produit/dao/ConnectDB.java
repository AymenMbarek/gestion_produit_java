/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.des.produit.dao;

import gestion.des.produit.modele.Achat;
import gestion.des.produit.modele.Client;
import gestion.des.produit.modele.Facture;
import gestion.des.produit.modele.Fornisseur;
import gestion.des.produit.modele.Stock;
import gestion.des.produit.modele.Vente;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AmineGasa
 */
public class ConnectDB {

    private final String User = "root";
    private final String password = "";
    String ipLocal = "localhost";

    private String url = "jdbc:mysql://" + ipLocal + ":3306/gestion_de_produit";
    private Connection cnx;
    private Statement st, jt;
    private ResultSet rst;

    public void DeleteAllElementTab(JTable T, int column, int row) {
        int i = 0;
        int j = 0;
        while (i < row) {
            while (j <= column) {
                T.setValueAt("", i, j);
                j++;
            }
            j = 0;
            i++;
        }

    }

    public String showDate() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(" YYYY-MM-dd");
        return sdf.format(d);

    }

    public String showDateTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        return sdf.format(d);

    }

    public void ConnexionIntoDataBase() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver OK");
            try {
                cnx = DriverManager.getConnection(url, User, password);
                System.out.println("successful connexion");
            } catch (SQLException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, " erreur connexion activer le serveur ou verifier "
                        + "\nle configuration de  serveur");

            } finally {
                exit();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int FindUser(String user, String pass) {
        int re = 0;
        try {
            st = cnx.createStatement();
            String sql = "select * from user where id_user=1";
            rst = (ResultSet) st.executeQuery(sql);
            while (rst.next()) {

                if (rst.getString("user").equals(user) && rst.getString("password").equals(pass)) {
                    re = 1;
                } else {
                    re = 0;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }

        return re;
    }

    public void UpdatUser(String s, String ss) {

        try {
            String sql = "Update user set user='" + s + "',password='" + ss + "'"
                    + "where id_user= 1";

            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertFornisseur(Fornisseur forn) {

        try {
            st = cnx.createStatement();
            String sql1 = "INSERT INTO `fornisseur`( `nom_prenom`, `N_telephone`, `Adresse`,`Ville`) "
                    + "VALUES ('" + forn.nom_prenom + "'," + "'" + forn.telephone + "','" + forn.adresse + "','" + forn.ville + "') ";
            st.executeUpdate(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ereur operation");
            exit();
        }

    }

    public void insertClient(Client cl) {
        try {
            st = cnx.createStatement();
            String sql1 = "INSERT INTO `client`( `nom_prenom`, `N_telephone`, `Adresse`) "
                    + "VALUES ('" + cl.nom_prenom + "'," + "'" + cl.telephone + "','" + cl.adresse + "') ";
            st.executeUpdate(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ereur operation");
            exit();
        }
    }

    public void AllFornisseur(JTable T) {
        try {
            st = cnx.createStatement();
            String sql = "select * from fornisseur";
            rst = (ResultSet) st.executeQuery(sql);
            int i = 0, j;

            while (rst.next()) {
                j = 0;
                T.setValueAt(rst.getInt("N_forn"), i, j);
                j++;
                T.setValueAt(rst.getString("nom_prenom").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("N_telephone").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("Adresse").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("Ville").toString(), i, j);
                j++;
                i++;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void AllClient(JTable T) {
        try {
            st = cnx.createStatement();
            String sql = "select * from client";
            rst = (ResultSet) st.executeQuery(sql);
            int i = 0, j;
            while (rst.next()) {
                j = 0;
                T.setValueAt(rst.getInt("N_client"), i, j);
                j++;
                T.setValueAt(rst.getString("nom_prenom").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("N_telephone").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("Adresse").toString(), i, j);
                j++;

                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void DeleteForn(int id) {
        String sql = "DELETE FROM fornisseur WHERE N_forn=" + id;
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdatForn(int id, Fornisseur fr) {

        try { st = cnx.createStatement();
            String sql = "Update fornisseur set nom_prenom='" + fr.nom_prenom + "',N_telephone='" + fr.telephone + "',"
                    + "Adresse='" + fr.adresse + "'" + ",Ville='" + fr.ville + "'"
                    + " where N_forn=" + id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DeleteClient(int id) {
        String sql = "DELETE FROM client WHERE N_client=" + id;
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdatClient(int id, Client cl) {

        try { st = cnx.createStatement();
            String sql = "Update client set  nom_prenom='" + cl.nom_prenom + "',N_telephone='" + cl.telephone + "',"
                    + "Adresse='" + cl.adresse + "'"
                    + "where N_client=" + id;
           
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void FindClient(JTable T, String word) {
        try {
            st = cnx.createStatement();
            String sql = "SELECT * FROM `client` WHERE nom_prenom like '" + word + "%'  ";
            rst = (ResultSet) st.executeQuery(sql);
            int i = 0, j;
            while (rst.next()) {
                j = 0;
                T.setValueAt(rst.getInt("N_client"), i, j);
                j++;
                T.setValueAt(rst.getString("nom_prenom").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("N_telephone").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("Adresse").toString(), i, j);
                j++;

                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void FindFornisseur(JTable T, String word, String recherche) {
        try {
            st = cnx.createStatement();
            String sql = "SELECT * FROM fornisseur WHERE " + word + " like '" + recherche + "%'  ";
            rst = (ResultSet) st.executeQuery(sql);
            int i = 0, j;
            while (rst.next()) {
                j = 0;
                T.setValueAt(rst.getInt("N_forn"), i, j);
                j++;
                T.setValueAt(rst.getString("nom_prenom").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("N_telephone").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("Adresse").toString(), i, j);
                j++;
                T.setValueAt(rst.getString("Ville").toString(), i, j);
                j++;
                i++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error reccuperation");
        }
    }

    public void FindNomPrenomFornis(JComboBox combo) {
        try {
            st = cnx.createStatement();
            String sql = "select nom_prenom  from fournisseur";
            rst = (ResultSet) st.executeQuery(sql);
            ArrayList<String> ar = new ArrayList<>();
            ArrayList<String> rpl = new ArrayList<>();

            while (rst.next()) {

                ar.add(rst.getString("nom_prenom").toString());
            }
            rpl = Rplace(ar);
            for (int i = 0; i < rpl.size(); i++) {
                combo.addItem(rpl.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in reccuperation");
        }

    }

    public float AllAchat(JTable T) {
        float f = 0;
        try {
            ArrayList<Achat> list = new ArrayList<Achat>();
            st = cnx.createStatement();
            String sql = "select * from achat";
            rst = (ResultSet) st.executeQuery(sql);
            //int i=0,j;
            while (rst.next()) {
                f = rst.getFloat("prix_total") + f;

                Achat ach = new Achat(rst.getInt("N_achat"), rst.getString("nom_prenom_forn"), rst.getString("designation"), rst.getInt("quantite"),
                        rst.getFloat("prix_total"), rst.getFloat("prix_unitaire"),
                        rst.getDate("date_achat") + "");
                list.add(ach);
            }

            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_achat();
                row[1] = list.get(k).getNom_prenom_forn();
                row[2] = list.get(k).getDesignation();
                row[3] = list.get(k).getQuantité();
                row[4] = list.get(k).getPrix_total();
                row[5] = df.format(list.get(k).getPrix_unitaire());
                row[6] = list.get(k).getDate_achat();
                mo.addRow(row);
            }
            list.clear();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
        return f;
    }

    public void inserAchat(Achat ach) {
        try {
            st = cnx.createStatement();
            String sql1 = "INSERT INTO `achat`( `nom_prenom_forn`, `designation`, `quantite`"
                    + ", `prix_total`, `prix_unitaire`, `date_achat`) "
                    + "VALUES ('" + ach.nom_prenom_forn + "'," + "'" + ach.designation + "','" + ach.quantité + "'"
                    + ",'" + ach.prix_total + "','" + ach.prix_total / ach.quantité + "','" + ach.date_achat + "') ";
            st.executeUpdate(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ereur operation");
            exit();
        }
    }

    public void UpdatAchat(int id, Achat ach) {

        try {
            String sql = "Update achat set nom_prenom_forn='" + ach.nom_prenom_forn + "',designation='" + ach.designation + "',"
                    + "quantite='" + ach.quantité + "'" + ",prix_total='" + ach.prix_total + "',"
                    + "prix_unitaire='" + ach.prix_total / ach.quantité + "'"
                    + "where N_achat=" + id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void DeleteAchat(int id) {
        String sql = "DELETE FROM achat WHERE N_achat=" + id;
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void FindAchat(JTable T, String word, String recherche, JLabel j1, JLabel j2) {
        int qte = 0;
        float prixT = 0;
        try {
            ArrayList<Achat> list = new ArrayList<Achat>();
            st = cnx.createStatement();
            String sql = "SELECT * FROM achat WHERE " + word + " like '" + recherche + "%'  ";
            rst = (ResultSet) st.executeQuery(sql);

            while (rst.next()) {
                qte = qte + rst.getInt("quantite");

                prixT = prixT + rst.getFloat("prix_total");

                Achat ach = new Achat(rst.getInt("N_achat"), rst.getString("nom_prenom_forn"), rst.getString("designation"), rst.getInt("quantite"),
                        rst.getFloat("prix_total"), rst.getFloat("prix_unitaire"),
                        rst.getDate("date_achat") + "");
                list.add(ach);
            }
            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            DecimalFormat df = new DecimalFormat("0.00");
            mo.setRowCount(0);
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_achat();
                row[1] = list.get(k).getNom_prenom_forn();
                row[2] = list.get(k).getDesignation();
                row[3] = list.get(k).getQuantité();
                row[4] = list.get(k).getPrix_total();
                row[5] = df.format(list.get(k).getPrix_unitaire());
                row[6] = list.get(k).getDate_achat();
                mo.addRow(row);
            }
            list.clear();
            j1.setText("qte totale " + qte);
            j2.setText("prix totale " + prixT);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void FindAchatDate(JTable T, String d1, String d2, JLabel j1, JLabel j2) {
        int qte = 0;
        float prixT = 0;
        try {
            ArrayList<Achat> list = new ArrayList<Achat>();
            st = cnx.createStatement();
            String sql = "SELECT * FROM achat WHERE date_achat between'" + d1 + "' and '" + d2 + "'  ";
            rst = (ResultSet) st.executeQuery(sql);
            while (rst.next()) {
                qte = qte + rst.getInt("quantite");
                prixT = prixT + rst.getFloat("prix_total");
                Achat ach = new Achat(rst.getInt("N_achat"), rst.getString("nom_prenom_forn"), rst.getString("designation"), rst.getInt("quantite"),
                        rst.getFloat("prix_total"), rst.getFloat("prix_unitaire"),
                        rst.getDate("date_achat") + "");
                list.add(ach);
            }
            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            DecimalFormat df = new DecimalFormat("0.00");
            mo.setRowCount(0);
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_achat();
                row[1] = list.get(k).getNom_prenom_forn();
                row[2] = list.get(k).getDesignation();
                row[3] = list.get(k).getQuantité();
                row[4] = list.get(k).getPrix_total();
                row[5] = df.format(list.get(k).getPrix_unitaire());
                row[6] = list.get(k).getDate_achat();
                mo.addRow(row);
            }
            list.clear();
            j1.setText("qte totale " + qte);
            j2.setText("prix totale " + prixT);

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void inserFacture(Facture fac, String etat) {
        try {
            float r = fac.montant_a_paye - fac.montant_verser;
            st = cnx.createStatement();
            String sql1 = "INSERT INTO `facture`( `nom_prenom_forn`, `montant_a_paye`, `montant_verser`"
                    + ",`montant_reste`, `date_versement`, `etat`) "
                    + "VALUES ('" + fac.nom_prenom_forn + "'," + "'" + fac.montant_a_paye + "','" + fac.montant_verser + "'"
                    + ",'" + r + "','" + showDate() + "','" + etat + "') ";
            st.executeUpdate(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ereur operation");
            exit();
        }
    }

    public void AllFacture(JTable T) {
        try {
            ArrayList<Facture> list = new ArrayList<Facture>();
            st = cnx.createStatement();
            String sql = "select * from facture";
            rst = (ResultSet) st.executeQuery(sql);

            while (rst.next()) {
                Facture fac = new Facture(rst.getInt("N_fact"), rst.getString("nom_prenom_forn"),
                        rst.getFloat("montant_a_paye"), rst.getFloat("montant_verser"),
                        rst.getFloat("montant_reste"), rst.getDate("date_versement") + "", rst.getString("etat"));
                list.add(fac);
            }
            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_fact();
                row[1] = list.get(k).getNom_prenom_forn();
                row[2] = list.get(k).getMontant_a_paye();
                row[3] = list.get(k).getMontant_verser();
                row[4] = list.get(k).getMontant_reste();
                row[5] = list.get(k).getDate_versement();
                row[6] = list.get(k).getEtat();
                mo.addRow(row);
            }
            list.clear();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void FindPrixTotale(String s, JTextField jt) {
        try {
            st = cnx.prepareStatement(url);
            String sql = "SELECT sum(prix_total) FROM `achat` WHERE nom_prenom_forn = '" + s + "'  ";
            st = cnx.prepareStatement(sql);
            rst = st.executeQuery(sql);
            if (rst.next()) {
                String f = rst.getString("sum(prix_total)");
                jt.setText(f + "");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void DeleteFacture(int id) {
        String sql = "DELETE FROM facture WHERE N_fact=" + id;
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdatFact(int id, Facture fc) {

        try {
            float reste = fc.montant_a_paye - fc.montant_verser;
            String etat;

            String sql = "Update facture set nom_prenom_forn='" + fc.nom_prenom_forn + "',montant_a_paye='" + fc.montant_a_paye + "',"
                    + "montant_verser='" + fc.montant_verser + "'" + ",montant_reste='" + reste + "',"
                    + "date_versement='" + showDate() + "',etat='" + fc.etat + "'"
                    + "where N_fact=" + id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void FindFact(JTable T, String SQL
    ) {

        try {
            ArrayList<Facture> list = new ArrayList<Facture>();
            st = cnx.createStatement();
            String sql = SQL;
            rst = (ResultSet) st.executeQuery(sql);

            while (rst.next()) {
                Facture fac = new Facture(rst.getInt("N_fact"), rst.getString("nom_prenom_forn"),
                        rst.getFloat("montant_a_paye"), rst.getFloat("montant_verser"),
                        rst.getFloat("montant_reste"), rst.getDate("date_versement") + "", rst.getString("etat"));
                list.add(fac);
            }
            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_fact();
                row[1] = list.get(k).getNom_prenom_forn();
                row[2] = list.get(k).getMontant_a_paye();
                row[3] = list.get(k).getMontant_verser();
                row[4] = list.get(k).getMontant_reste();
                row[5] = list.get(k).getDate_versement();
                row[6] = list.get(k).getEtat();
                mo.addRow(row);
            }
            list.clear();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public String FindPrixTotaleRESTE() {
        String f = "";
        try {
            st = cnx.prepareStatement(url);
            String sql = "SELECT sum(montant_reste) FROM `facture` WHERE etat = 'non servir'  ";
            st = cnx.prepareStatement(sql);
            rst = st.executeQuery(sql);
            if (rst.next()) {
                f = rst.getString("sum(montant_reste)");

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
        return f;
    }

    public void FindDesignation(JComboBox combo) {
        try {
            st = cnx.createStatement();
            String sql = "select designation  from achat";
            rst = (ResultSet) st.executeQuery(sql);
            ArrayList<String> ar = new ArrayList<>();
            ArrayList<String> rpl = new ArrayList<>();
            while (rst.next()) {
                ar.add(rst.getString("designation").toString());

            }
            rpl = Rplace(ar);
            for (int i = 0; i < rpl.size(); i++) {
                combo.addItem(rpl.get(i));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }

    }

    ArrayList<String> Rplace(ArrayList<String> ar) {
        ArrayList<String> rpl = new ArrayList<>();
        int K = 0;
        boolean test;
        while (K < ar.size()) {
            test = true;
            for (int i = 0; i < rpl.size(); i++) {
                if (ar.get(K).equals(rpl.get(i))) {
                    test = false;
                }
            }
            if (test) {
                rpl.add(ar.get(K));
            }
            K++;
        }

        return rpl;
    }

    public void FindQteTotal(String sql, String sql1, JTextField jt) {
        try {
            st = cnx.prepareStatement(url);

            st = cnx.prepareStatement(sql);
            rst = st.executeQuery(sql);
            //  DecimalFormat df=new DecimalFormat("0.00");
            if (rst.next()) {

                float f = rst.getFloat(sql1);
                jt.setText(/*df.format(*/f/*)*/ + "");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }
    ArrayList<String> DES = new ArrayList<>();

    public void AllStock(JTable T) {

        try {
            ArrayList<Stock> list = new ArrayList<Stock>();
            st = cnx.createStatement();
            String sql = "select * from stock";
            rst = (ResultSet) st.executeQuery(sql);
            DES.clear();
            while (rst.next()) {

                Stock ST = new Stock(rst.getInt("N_prod"), rst.getString("designation"),
                        rst.getString("categorie"), rst.getString("unite"),
                        rst.getDate("date_exp") + "", rst.getInt("qte_totale"),
                        rst.getInt("qte_vente"), rst.getFloat("prix_vent"),
                        rst.getFloat("prix_unt")
                );
                list.add(ST);
            }

            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");
            Object row[] = new Object[11];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_prod();
                row[1] = list.get(k).getDesignation();
                DES.add(list.get(k).getDesignation());
                row[2] = list.get(k).getCategorie();
                row[3] = list.get(k).getUnite();
                row[4] = list.get(k).getDate_exp();
                row[5] = df.format(list.get(k).getQte_totale());
                row[6] = list.get(k).getQte_vente();
                row[7] = list.get(k).getQte_reste();
                row[8] = /*df.format(*/ list.get(k).getPrix_unt()/*)*/;
                row[9] = list.get(k).getPrix_vent();
                row[10] = list.get(k).getGain();

                mo.addRow(row);
            }
            list.clear();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public ArrayList<String> CONTROLE() {

        ArrayList<String> rpl = new ArrayList<>();
        ArrayList<String> rpl1 = new ArrayList<>();
        int K = 0;
        boolean test;
        while (K < DES.size()) {
            test = true;
            for (int i = 0; i < rpl.size(); i++) {
                if (DES.get(K).equals(rpl.get(i))) {
                    test = false;
                }
            }
            if (test) {
                rpl.add(DES.get(K));
            }
            if (test == false) {
                rpl1.add(DES.get(K));
            }
            K++;
        }

        return rpl1;
    }

    public ArrayList<String> CONTROLE2(JComboBox com) {

        ArrayList<String> rpl = new ArrayList<>();

        int K = 0;
        boolean test;
        while (K < com.getItemCount()) {
            test = true;
            for (int i = 0; i < DES.size(); i++) {
                if (com.getItemAt(K).toString().equals(DES.get(i))) {
                    test = false;
                }
            }
            if (test) {
                rpl.add((String) com.getItemAt(K));
            }
            K++;
        }

        return rpl;
    }

    public void insertStock(Stock ST, int s) {

        try {
            System.out.println(ST.getDesignation() + " " + ST.getCategorie()
                    + " " + ST.getUnite() + " " + ST.getDate_exp() + " HH" + ST.getQte_totale()
                    + " " + ST.getQte_vente() + " " + ST.getQte_reste() + " " + " " + ST.getPrix_unt()
                    + " " + ST.getPrix_vent() + " " + ST.getGain());
            st = cnx.createStatement();
            String sql;
            if (s == 1) {
                sql = "INSERT INTO stock(  designation, categorie,unite,"
                        + "date_exp,qte_totale,qte_vente,qte_reste,prix_unt,"
                        + "prix_vent,gain) "
                        + "VALUES ('" + ST.getDesignation() + "'," + "'" + ST.getCategorie()
                        + "','" + ST.getUnite() + "','" + ST.getDate_exp()
                        + "','" + ST.getQte_totale() + "','" + ST.getQte_vente() + "','" + ST.getQte_reste()
                        + "','" + ST.getPrix_unt() + "','" + ST.getPrix_vent() + "','" + ST.getGain() + "') ";
            } else {
                sql = "INSERT INTO stock(  designation, categorie,unite,"
                        + "qte_totale,qte_vente,qte_reste,prix_unt,"
                        + "prix_vent,gain) "
                        + "VALUES ('" + ST.getDesignation() + "'," + "'" + ST.getCategorie()
                        + "','" + ST.getUnite() + "'"
                        + ",'" + ST.getQte_totale() + "','" + ST.getQte_vente() + "','" + ST.getQte_reste()
                        + "','" + ST.getPrix_unt() + "','" + ST.getPrix_vent() + "','" + ST.getGain() + "') ";
            }
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ereur operation");
            exit();
        }

    }

    public void DeleteStock(int id) {
        String sql = "DELETE FROM stock WHERE N_prod=" + id;
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdatStock(String sql) {

        try {

            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void FindStock(JTable T, String sql) {

        try {
            ArrayList<Stock> list = new ArrayList<Stock>();
            st = cnx.createStatement();

            rst = (ResultSet) st.executeQuery(sql);
            while (rst.next()) {

                Stock ST = new Stock(rst.getInt("N_prod"), rst.getString("designation"),
                        rst.getString("categorie"), rst.getString("unite"),
                        rst.getDate("date_exp") + "", rst.getInt("qte_totale"),
                        rst.getInt("qte_vente"), rst.getFloat("prix_vent"),
                        rst.getFloat("prix_unt")
                );
                list.add(ST);
            }

            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");
            Object row[] = new Object[11];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_prod();
                row[1] = list.get(k).getDesignation();

                row[2] = list.get(k).getCategorie();
                row[3] = list.get(k).getUnite();
                row[4] = list.get(k).getDate_exp();
                row[5] = df.format(list.get(k).getQte_totale());
                row[6] = list.get(k).getQte_vente();
                row[7] = list.get(k).getQte_reste();
                row[8] = /*df.format(*/ list.get(k).getPrix_unt()/*)*/;
                row[9] = list.get(k).getPrix_vent();
                row[10] = list.get(k).getGain();

                mo.addRow(row);
            }
            list.clear();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void AllStockVente(JTable T) {

        try {
            ArrayList<Stock> list = new ArrayList<Stock>();
            st = cnx.createStatement();
            String sql = "select N_prod,designation,qte_totale,"
                    + " qte_vente,qte_reste,prix_vent,gain from stock";
            rst = (ResultSet) st.executeQuery(sql);
            DES.clear();
            while (rst.next()) {
                Stock ST = new Stock(rst.getInt("N_prod"), rst.getString("designation"),
                        rst.getInt("qte_totale"),
                        rst.getInt("qte_vente"), rst.getInt("qte_reste"),
                        rst.getFloat("prix_vent"), rst.getFloat("gain")
                );

                list.add(ST);
            }

            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_prod();
                row[1] = list.get(k).getDesignation();

                row[2] = df.format(list.get(k).getQte_totale());
                row[3] = list.get(k).getQte_vente();
                row[4] = list.get(k).getQte_reste();

                row[5] = list.get(k).getPrix_vent();
                row[6] = list.get(k).getGain();

                mo.addRow(row);
            }
            list.clear();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }

    public void AllStockVenteRcherche(JTable T, String S) {

        try {
            ArrayList<Stock> list = new ArrayList<Stock>();
            st = cnx.createStatement();
            String sql = "select N_prod,designation,qte_totale,"
                    + " qte_vente,qte_reste,prix_vent,prix_unt,gain from stock where "
                    + "designation like '" + S + "%'";
            rst = (ResultSet) st.executeQuery(sql);
            DES.clear();
            while (rst.next()) {

                Stock ST = new Stock(rst.getInt("N_prod"), rst.getString("designation"),
                        rst.getInt("qte_totale"),
                        rst.getInt("qte_vente"), rst.getInt("qte_reste"),
                        rst.getFloat("prix_vent"), rst.getFloat("gain")
                );
                list.add(ST);
            }

            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_prod();
                row[1] = list.get(k).getDesignation();

                row[2] = df.format(list.get(k).getQte_totale());
                row[3] = list.get(k).getQte_vente();
                row[4] = list.get(k).getQte_reste();

                row[5] = list.get(k).getPrix_vent();
                row[6] = list.get(k).getGain();

                mo.addRow(row);
            }
            list.clear();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }
    public void UpdateQteResteMinus( int id,int qte) {
        try {
            st = cnx.prepareStatement(url);
            String sql = "SELECT qte_totale,qte_vente FROM `stock` WHERE N_prod = " + id + "  ";
            st = cnx.prepareStatement(sql);
            rst = st.executeQuery(sql);
            if (rst.next()) {
                int totale = rst.getInt("qte_totale");
                int vente = rst.getInt("qte_vente")+qte;
                
                String sql2 = "Update stock set qte_vente='" + vente + "',qte_reste='" 
                        + (totale-vente) + "'"
                    + "where N_prod="+id;

            st.executeUpdate(sql2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }
    public void UpdateQteResteAdd( String des,int qte) {
        try {
            st = cnx.prepareStatement(url);
            String sql = "select Qte_reste,qte_vente,designation FROM `stock` WHERE designation = '" + des + "'  ";
            st = cnx.prepareStatement(sql);
            rst = st.executeQuery(sql);
            if (rst.next()) {
                int vente = rst.getInt("qte_vente")-qte;
                int reste = rst.getInt("Qte_reste")+qte;
                
                String sql2 = "Update stock set qte_vente='" + vente + "',qte_reste='" 
                        + reste + "'"
                    + "where designation='"+des+"'";

            st.executeUpdate(sql2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }
     public void FindNomPrenomClient(JComboBox combo) {
        try {
            st = cnx.createStatement();
            String sql = "select nom_prenom  from client";
            rst = (ResultSet) st.executeQuery(sql);
            ArrayList<String> ar = new ArrayList<>();
            ArrayList<String> rpl = new ArrayList<>();

            while (rst.next()) {

                ar.add(rst.getString("nom_prenom").toString());
            }
            rpl = Rplace(ar);
            for (int i = 0; i < rpl.size(); i++) {
                combo.addItem(rpl.get(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }

    }
      public void insertVente(Vente V) {

        try {
            st = cnx.createStatement();
            String sql1 = "INSERT INTO vente( nom_prenom_client, commentaire, marchendise,prix_a_payez,benefice,date_vente) "
                    + "VALUES ('" + V.getNom_prenom() + "'," + "'" + V.getCommentaire() + "','" + V.getMarchendise() + "','" + V.getPrix_a_payez()
                    + "','"+V.getBenefice()+"','"+V.getDate()+"')";
            st.executeUpdate(sql1);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "ereur operation");
            exit();
        }

    }
        public void AllVente(JTable T) {

        try {
            ArrayList<Vente> list = new ArrayList<Vente>();
            st = cnx.createStatement();
            String sql = "select * from vente";
            rst = (ResultSet) st.executeQuery(sql);
            
            while (rst.next()) {
String s="";s= rst.getDate("date_vente")+""+" " +rst.getTime("date_vente")+""; 
                Vente V = new Vente(rst.getInt("N_vente"), rst.getString("nom_prenom_client"),
                        rst.getString("commentaire"), rst.getString("marchendise"),
                         rst.getFloat("prix_a_payez"),rst.getFloat("benefice"),
                  s  );
                list.add(V);
            }

            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_vente();
                row[1] = list.get(k).getNom_prenom();
               
                row[2] = list.get(k).getCommentaire();
                row[3] = list.get(k).getMarchendise();
             
                row[4] = df.format(list.get(k).getPrix_a_payez());
                row[5] = df.format(list.get(k).getBenefice());
                row[6] = /*df.format(*/ list.get(k).getDate()/*)*/;
                

                mo.addRow(row);
            }
           // list.clear();

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }
            public void FindVente(JTable T,String sql,JLabel j1,JLabel j2) {

        try {
            ArrayList<Vente> list = new ArrayList<Vente>();
            st = cnx.createStatement();
            
            rst = (ResultSet) st.executeQuery(sql);
           float prix=0,benefice=0;int client =0;
            while (rst.next()) {
String s="";s= rst.getDate("date_vente")+""+" " +rst.getTime("date_vente")+""; 
                Vente V = new Vente(rst.getInt("N_vente"), rst.getString("nom_prenom_client"),
                        rst.getString("commentaire"), rst.getString("marchendise"),
                         rst.getFloat("prix_a_payez"),rst.getFloat("benefice"),
                  s  );
                prix=prix+rst.getFloat("prix_a_payez");
                benefice=rst.getFloat("benefice")+benefice;
                client++;        
                list.add(V);
            }

            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");
            Object row[] = new Object[7];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getN_vente();
                row[1] = list.get(k).getNom_prenom();
               
                row[2] = list.get(k).getCommentaire();
                row[3] = list.get(k).getMarchendise();
             
                row[4] = df.format(list.get(k).getPrix_a_payez());
                row[5] = df.format(list.get(k).getBenefice());
                row[6] = /*df.format(*/ list.get(k).getDate()/*)*/;
                

                mo.addRow(row);
            }
           j1.setText("prix V T: "+prix);
            j2.setText("bénifice: "+benefice+" Nb client: "+client);

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " error in recuperitaion");
        }
    }
            public void UpdatVente(int id, String s1,String s2) {

        try {
            String sql = "Update vente set nom_prenom_client='" + s1+ "',commentaire='" +s2 + "'"
                    
                    + " where  	N_vente=" + id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
             public void DeleteVente(int id) {
        String sql = "DELETE FROM vente WHERE  	N_vente=" + id;
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
