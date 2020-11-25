package com_rib;

import java.io.Serializable;

import commun.Chaine;
import commun.ParameterHolder_SageErreur;
import commun.SageErreur;
import commun.interfaces.SO_GestionErreurs_proxy;

import displayproject.binding.beans.Observable;
import ergoconst.Constants;
import framework.Array_Of_TextData;
import framework.CloneHelper;
import framework.ParameterHolder_TextData;
import framework.ParameterHolder_integer;
import framework.ParameterHolder_string;
import framework.RuntimeProperties;
import framework.TextData;
import framework.TextNullable;

/**
 * RIB_CDM<p>
 * <p>
 * @author Generated from Forte
 * @since  16-Sep-2008
 */
@RuntimeProperties(isDistributed=false, isAnchored=false, isShared=false, isTransactional=false)
 
public class RIB_CDM
        extends RIB_Maroc
        implements Serializable, Observable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5368521005947670585L;
	// ----------
    // Attributes
    // ----------
    private TextData zoneVide;
    private TextData numeroCompte;

    // ------------
    // Constructors
    // ------------
    public RIB_CDM() {
        // Explicitly call the superclass constructor to prevent the implicit call
        super();
        this.setNumeroCompte(new TextData());
        this.setZoneVide(new TextData());

    }

    // ----------------------
    // Accessors and Mutators
    // ----------------------
    public void setZoneVide(TextData zoneVide) {
        TextData oldValue = this.zoneVide;
        this.zoneVide = zoneVide;
        if (this.qq_Listeners != null) this.qq_Listeners.firePropertyChange("zoneVide", oldValue, this.zoneVide);
    }

    public TextData getZoneVide() {
        return this.zoneVide;
    }

    public void setNumeroCompte(TextData numeroCompte) {
        TextData oldValue = this.numeroCompte;
        this.numeroCompte = numeroCompte;
        if (this.qq_Listeners != null) this.qq_Listeners.firePropertyChange("numeroCompte", oldValue, this.numeroCompte);
    }

    public TextData getNumeroCompte() {
        return this.numeroCompte;
    }

    // -------
    // Methods
    // -------
    /**
     * afficher<p>
     * <p>
     * @param chRIB Type: TextData
     * @return int
     */
    public int afficher(ParameterHolder_TextData chRIB) {
        chRIB.setObject(new TextData());
        ((TextData)chRIB.getObject()).setValue( this.getCodeBanque() );
        ((TextData)chRIB.getObject()).concat(" ");
        //if CodeVille = Nil or CodeVille.Value='N/A' or CodeVille.Value='   ' or CodeVille.Value=''  then
        //	CodeVille='000';
        //end if;
        ((TextData)chRIB.getObject()).concat(this.getCodeVille());
        ((TextData)chRIB.getObject()).concat(" ");
        ((TextData)chRIB.getObject()).concat(this.getZoneVide());
        ((TextData)chRIB.getObject()).concat(" ");
        ((TextData)chRIB.getObject()).concat(this.getNumeroCompte());
        ((TextData)chRIB.getObject()).concat(" ");
        ((TextData)chRIB.getObject()).concat(this.getClefRIB());
        return Constants.CODERETOUROK;
    }

    /**
     * calculerClef<p>
     * bug 2007232 : ne pas modifier les lignes jusqu'au commentaire Fin bug 2007232<br>
     * <p>
     * @param clef Type: String
     * @param erreur Type: SageErreur
     * @return int
     */
    public int calculerClef(ParameterHolder_string clef, ParameterHolder_SageErreur erreur) {
        clef.setObject("");
        // Fin bug 2007232

        int ret = 0;

        Chaine chRIB = new Chaine();

        // Récupération de la valeur du RIB
        // -------------------------------
        // Parameters for call to Afficher
        // -------------------------------
        ParameterHolder_TextData qq_ChRIB = new ParameterHolder_TextData();
        int qq_Afficher = this.afficher(qq_ChRIB);
        chRIB.setValeur((TextData)qq_ChRIB.getObject());
        ret = qq_Afficher;
        chRIB.enleverCaracteres(" ");

        if (chRIB.getValeur().getActualSize() < 22) {
            clef.setObject("");
            return Constants.CODERETOUROK;
        }

        // Remplacer la clef par 00
        chRIB.getValeur().cutRange(22);
        chRIB.getValeur().concat("00");

        // Remplacer les caractères alphabétiques par des chiffres
        // -------------------------------------
        // Parameters for call to RemplacerAlpha
        // -------------------------------------
        ParameterHolder_TextData qq_ChRIB1 = new ParameterHolder_TextData(chRIB.getValeur());
        int qq_RemplacerAlpha = this.remplacerAlpha(qq_ChRIB1);
        chRIB.setValeur((TextData)qq_ChRIB1.getObject());
        ret = qq_RemplacerAlpha;

        // Calcul du reste de la division du RIB  ainsi transformé par 97
        int reste = 0;
        // ------------------------------------
        // Parameters for call to CalculerReste
        // ------------------------------------
        ParameterHolder_integer qq_Reste = new ParameterHolder_integer();
        int qq_CalculerReste = this.calculerReste(chRIB.getValeur(), 97, qq_Reste);
        reste = qq_Reste.getInt();
        ret = qq_CalculerReste;
        if (ret == Constants.CODERETOURKO) {
            return Constants.CODERETOURKO;
        }

        // Calcul du complément du reste par rapport à 97
        TextData txt = new TextData();
        txt.setValue(97-reste);

        // si le nombre obtenu est composé d'un seul chiffre, rajouter un 0 devant 
        if (txt.getActualSize() == 1) {
            TextData txt2 = new TextData("0");
            txt2.concat(txt);
            clef.setObject(txt2.getValue());
            // str 14/01/97
            // si tu m'écrases ma clef, comment je sais si elle est bonne ?
            //	self.ClefRIB = Txt2.Value;
        }
        else {
            clef.setObject(txt.getValue());
            // idem
            //	self.ClefRIB = Txt.Value;
        }
        return Constants.CODERETOUROK;
    }

    /**
     * calculerClefHYB<p>
     * bug 2007232 : ne pas modifier les lignes jusqu'au commentaire Fin bug 2007232<br>
     * <p>
     * @param clef Type: String
     * @param erreur Type: SageErreur
     * @return int
     */
    public int calculerClefHYB(ParameterHolder_string clef, ParameterHolder_SageErreur erreur) {
        clef.setObject("");
        // Fin bug 2007232

        int ret = 0;

        Chaine chRIB = new Chaine();

        // Récupération de la valeur du RIB
        // -------------------------------
        // Parameters for call to Afficher
        // -------------------------------
        ParameterHolder_TextData qq_ChRIB = new ParameterHolder_TextData();
        int qq_Afficher = this.afficher(qq_ChRIB);
        chRIB.setValeur((TextData)qq_ChRIB.getObject());
        ret = qq_Afficher;
        chRIB.enleverCaracteres(" ");

        if (chRIB.getValeur().getActualSize() < 21) {
            clef.setObject("");
            return Constants.CODERETOUROK;
        }

        // Remplacer la clef par 00
        chRIB.getValeur().cutRange(21);
        chRIB.getValeur().concat("00");

        // Remplacer les caractères alphabétiques par des chiffres
        // -------------------------------------
        // Parameters for call to RemplacerAlpha
        // -------------------------------------
        ParameterHolder_TextData qq_ChRIB1 = new ParameterHolder_TextData(chRIB.getValeur());
        int qq_RemplacerAlpha = this.remplacerAlpha(qq_ChRIB1);
        chRIB.setValeur((TextData)qq_ChRIB1.getObject());
        ret = qq_RemplacerAlpha;

        // Calcul du reste de la division du RIB  ainsi transformé par 97
        int reste = 0;
        // ------------------------------------
        // Parameters for call to CalculerReste
        // ------------------------------------
        ParameterHolder_integer qq_Reste = new ParameterHolder_integer();
        int qq_CalculerReste = this.calculerReste(chRIB.getValeur(), 97, qq_Reste);
        reste = qq_Reste.getInt();
        ret = qq_CalculerReste;
        if (ret == Constants.CODERETOURKO) {
            return Constants.CODERETOURKO;
        }

        // Calcul du complément du reste par rapport à 97
        TextData txt = new TextData();
        txt.setValue(97-reste);

        // si le nombre obtenu est composé d'un seul chiffre, rajouter un 0 devant 
        if (txt.getActualSize() == 1) {
            TextData txt2 = new TextData("0");
            txt2.concat(txt);
            clef.setObject(txt2.getValue());
            // str 14/01/97
            // si tu m'écrases ma clef, comment je sais si elle est bonne ?
            //	self.ClefRIB = Txt2.Value;
        }
        else {
            clef.setObject(txt.getValue());
            // idem
            //	self.ClefRIB = Txt.Value;
        }
        return Constants.CODERETOUROK;
    }

    /**
     * calculerRIB_DuCompte<p>
     * <p>
     * @param numCompte Type: String
     * @param erreur Type: SageErreur
     * @return int
     */
    public int calculerRIB_DuCompte(String numCompte, ParameterHolder_SageErreur erreur) {
        return Constants.CODERETOUROK;
    }

    /**
     * caracteriserErgo<p>
     * <p>
     * @return TextData
     */
    public TextData caracteriserErgo() {
        TextData txt = new TextData();
        txt.setValue("&&& &&& 0000 &&&&&&&&&&&& &&");
        return txt;
    }

    /**
     * charger<p>
     * <p>
     * @param valeurEntree Type: TextData
     * @param erreur Type: SageErreur
     * @return int
     */
    public int charger(TextData valeurEntree, ParameterHolder_SageErreur erreur) {
        Chaine ch_RIB = new Chaine(CloneHelper.clone(valeurEntree, true));
        ch_RIB.enleverCaracteres(" ");

        switch (ch_RIB.getValeur().getActualSize()) {
            case 22: {
                ch_RIB.getValeur().concat("00");
                break;
            }
            case 21: {
                ch_RIB.getValeur().concat("000");
                break;
            }
            case 24: {
                ch_RIB.getValeur().concat("");
                break;
            }

            default: {
                erreur.setObject(SO_GestionErreurs_proxy.getInstance().creerErreur(new TextData("00076"), Constants.CR_INFO, (Array_Of_TextData<TextData>)null));
                return Constants.CODERETOURKO;
            }
        }

        this.setCodeBanque(ch_RIB.getValeur().copyRange(0, 3));
        this.setCodeVille(ch_RIB.getValeur().copyRange(3, 6));
        this.setZoneVide(ch_RIB.getValeur().copyRange(6, 10));
        this.setNumeroCompte(ch_RIB.getValeur().copyRange(10, 22));
        this.setClefRIB(ch_RIB.getValeur().copyRange(22));

        //-return VerifierValidite(Erreur);
        return Constants.CODERETOUROK;
    }

    /**
     * chargerSansCle<p>
     * <p>
     * @param valeurEntree Type: TextNullable
     * @param erreur Type: SageErreur
     * @return int
     */
    public int chargerSansCle(TextNullable valeurEntree, ParameterHolder_SageErreur erreur) {
        int ret = 0;
        SageErreur err = null;

        // Longueur du RIB
        int qq_long = 0;

        // Contient la chaine contenue dans ValeurEntrée sans les blancs
        Chaine ch_RIB = new Chaine();
        ch_RIB.setValeur(CloneHelper.clone(valeurEntree, true));

        ch_RIB.enleverCaracteres(" ");
        qq_long = ch_RIB.getValeur().getActualSize();

        if (qq_long == 22) {
            ch_RIB.getValeur().concat("00");
        }
        else if (qq_long != 24) {
            return Constants.CODERETOURKO;
        }
        // renseignement des attributs de l'instance courante de RIB_CDM
        this.setCodeBanque(new TextData((ch_RIB.getValeur().copyRange(0, 3)).getValue()));
        this.setCodeVille(new TextData((ch_RIB.getValeur().copyRange(3, 6)).getValue()));
        this.setZoneVide(new TextData((ch_RIB.getValeur().copyRange(6, 10)).getValue()));
        this.setNumeroCompte(new TextData((ch_RIB.getValeur().copyRange(10, 22)).getValue()));
        this.setClefRIB(new TextData((ch_RIB.getValeur().copyRange(22)).getValue()));

        //-ret = VerifierValiditeSansCle(Erreur);
        //-return(ret);
        return Constants.CODERETOUROK;
    }

    /**
     * chargerSansTout<p>
     * Charge le RIB avec peut-être pas tous les éléments<br>
     * <p>
     * Longueur du RIB<br>
     * <p>
     * @param valeurEntree Type: TextData
     * @param errSage Type: SageErreur
     * @return int
     */
    public int chargerSansTout(TextData valeurEntree, ParameterHolder_SageErreur errSage) {
        int qq_long = 0;

        // Contient la chaine contenue dans ValeurEntrée sans les blancs
        Chaine ch_RIB = new Chaine();
        ch_RIB.setValeur(CloneHelper.clone(valeurEntree, true));

        ch_RIB.enleverCaracteres(" ");
        qq_long = ch_RIB.getValeur().getActualSize();

        if (qq_long >= 3) {
            this.setCodeBanque(new TextData((ch_RIB.getValeur().copyRange(0, 3)).getValue()));
        }
        if (qq_long >= 6) {
            this.setCodeVille(new TextData((ch_RIB.getValeur().copyRange(3, 6)).getValue()));
        }
        if (qq_long >= 10) {
            this.setZoneVide(new TextData((ch_RIB.getValeur().copyRange(6, 10)).getValue()));
        }
        if (qq_long >= 22) {
            this.setNumeroCompte(new TextData((ch_RIB.getValeur().copyRange(10, 22)).getValue()));
        }
        if (qq_long >= 24) {
            this.setClefRIB(new TextData((ch_RIB.getValeur().copyRange(22)).getValue()));
        }

        return Constants.CODERETOUROK;
    }

    /**
     * getAPlat<p>
     * <p>
     * @return TextData
     */
    public TextData getAPlat() {
        TextData txt = new TextData();
        txt.setValue( this.getCodeBanque() );
        txt.concat(this.getCodeVille());
        txt.concat(this.getZoneVide());
        txt.concat(this.getNumeroCompte());
        txt.concat(this.getClefRIB());
        return txt;
    }

    /**
     * getCompte<p>
     * <p>
     * @return TextData
     */
    public TextData getCompte() {
        return this.getNumeroCompte();
    }

    // Method GetZoneVide() : TextData skipped because it is replaced by accessor / mutator.
    /**
     * verifierNumeroCompte<p>
     * <p>
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierNumeroCompte(ParameterHolder_SageErreur erreur) {
        return Constants.CODERETOUROK;
    }

    /**
     * verifierValidite<p>
     * <p>
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierValidite(ParameterHolder_SageErreur erreur) {
        return Constants.CODERETOUROK;
    }

    /**
     * verifierValiditeSansCle<p>
     * <p>
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierValiditeSansCle(ParameterHolder_SageErreur erreur) {
        return Constants.CODERETOUROK;
    }

    /**
     * verifierZoneVide<p>
     * <p>
     * @param erreur Type: SageErreur
     * @return int
     */
    public int verifierZoneVide(ParameterHolder_SageErreur erreur) {
        return Constants.CODERETOUROK;
    }
}	// end class RIB_CDM
// c Pass 2 Conversion Time: 4727 milliseconds
