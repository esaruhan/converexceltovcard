package com.limagiran.exceltovcard.control;

import static com.limagiran.exceltovcard.i18n.I18N.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinicius Silva
 */
public class VCard {

    private static final String BEGIN = "BEGIN:VCARD\n";
    private static final String VERSION = "VERSION:3.0\n";
    private static final String NAME_SURNAME = "N:;?;;;\n";
    private static final String CITY    = "N:?;?;;;;\n";
    private static final String NAME_2 = "FN:?\n";
    private static final String CELL = "TEL;type=CELL:?\n";
    private static final String PHONE = "TEL;type=HOME:?\n";
    private static final String ADDRESS = "ADR;TYPE=HOME,PREF:;;?;;;;\n";
    private static final String EMAIL = "EMAIL;TYPE=PREF,INTERNET:?\n";
    private static final String END = "END:VCARD\n";

    private String name = "";
    private String surname = "";
    private String city = "";
    private List<String> cell = new ArrayList<>();
    private List<String> phone = new ArrayList<>();
    private String address = "";
    private List<String> email = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }

    /**
     * Cria e retorna o conteúdo do arquivo .vcf
     *
     * @return conteúdo do arquivo .vcf pronto para ser salvo em disco e
     * utilizado
     */
    public String getVCard() {
        StringBuilder vCard = new StringBuilder();
        String name = getName();
        String surname = getSurname();
        //String surnameFirst = NAME_SURNAME.replace("?", getSurname());
        //String nameLater = surnameFirst.replace("?", getName());
        
        String nameLater = NAME_SURNAME.replace("?", getName());
        vCard.append(BEGIN)
                .append(VERSION)
                .append(nameLater)
                .append(NAME_2.replace("?", getName()))
                .append(ADDRESS.replace("?", getCity()))
                .append(getAll(getCell(), CELL))
                .append(getAll(getPhone(), PHONE))
                //.append(ADDRESS.replace("?", getAddress()))
                //.append(getAll(getEmail(), EMAIL))
                .append(END);
        return vCard.toString();
    }

    /**
     * Adiciona um atributo ao vCard
     *
     * @param value valor
     * @param type tipo do atributo
     * @throws NullPointerException se {@code type} is null
     */
    public void put(String value, EnumTypeName type) throws NullPointerException {
        switch (type) {
            case NAME:
                setName(value);
                break;
            case SURNAME:
                setSurname(value);
                break;
            case CEL:
                addCell(value);
                break;
            case TEL:
                addPhone(value);
                break;
            case ADDRESS:
                setCity(value);
                break;
            case EMAIL:
                addEmail(value);
                break;
        }
    }

    /**
     * @return o nome
     */
    public String getName() {
        return name;
    }

    /**
     * @param name novo nome
     */
    public void setName(String name) {
        this.name = name;
    }
    
      /**
     * @return o surname
     */
    public String getSurname() {
        return surname;
    }
    /**
     * @param surname novo nome
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
      /**
     * @return o city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city novo nome
     */
    public void setCity(String city) {
        this.city = city;
    }
    

    /**
     * @return os telefones
     */
    public List<String> getPhone() {
        List<String> newList = new ArrayList<String>();
        for(String str : phone ){
            str = str.replace(" ", "");
            str = str.replace(")", "");
            str = str.replace("(", "");
            str = str.replace("-", "");
            str = str.replace(" ", "");
            
            if(!str.startsWith("0")){
               str = "0"+str;
            }
            newList.add(str);
            
            System.out.println("Yeni numaralar :"+str);
            
        }
        return newList;
    }

    /**
     * @param phone telefone a ser adicionado
     */
    public void addPhone(String phone) {
        this.phone.add(phone);
    }

    /**
     * @return o endereço
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address o novo endereço
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return os celulares
     */
    public List<String> getCell() {
        List<String> newList = new ArrayList<String>();
        for(String str : cell ){
            str = str.replace(" ", "");
            str = str.replace(")", "");
            str = str.replace("(", "");
            str = str.replace("-", "");
            str = str.replace(" ", "");
            
            if(!str.startsWith("0")){
               str = "0"+str;
            }
            newList.add(str);
            
            System.out.println("Yeni numaralar :"+str);
            
        }
        return newList;
    }

    /**
     * @param cell celular a ser adicionado
     */
    public void addCell(String cell) {
        this.cell.add(cell);
    }

    /**
     * @return o e-mail
     */
    public List<String> getEmail() {
        return email;
    }

    /**
     * @param email e-mail a ser adicionado
     */
    public void addEmail(String email) {
        this.email.add(email);
    }

    /**
     * Formatar uma lista de dados
     *
     * @param list lista de dados
     * @param code modelo de código
     * @return uma linha com um modelo de código preenchido para cada dado da
     * lista de dados
     */
    private String getAll(List<String> list, String code) {
        StringBuilder _return = new StringBuilder();
        list.stream().forEach(val -> _return.append(code.replace("?", val)));
        return _return.toString();
    }

    /**
     * Enum com os tipos de dados permitidos
     */
    public static enum EnumTypeName {
        EMPTY(""), NAME(ENUM_TYPE_NAME_NAME),SURNAME(ENUM_TYPE_NAME_SURNAME),
        CEL(ENUM_TYPE_NAME_CEL), TEL(ENUM_TYPE_NAME_TEL),
        ADDRESS(ENUM_TYPE_NAME_ADDRESS), EMAIL(ENUM_TYPE_NAME_EMAIL);

        /**
         * Nome do tipo
         */
        public final String name;

        /**
         * @param name nome do tipo de dado
         * @throws se name == null
         */
        private EnumTypeName(Object name) throws NullPointerException {
            this.name = name.toString();
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
