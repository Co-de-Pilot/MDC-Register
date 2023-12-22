package hu.co_de_pilot.mdcregister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Countries {
	
	AFGHANISTAN("Afganisztán"),
	ALBANIA("Albánia"),
	ALGERIA("Algéria"),
	ANDORRA("Andorra"),
	ANGOLA("Angola"),
	ARGENTINA("Argentína"),
	ARMENIA("Örményország"),
	AUSTRALIA("Ausztrália"),
	AZERBAIJAN("Azerbajdzsán"),
	BAHAMAS("Bahama-szigetek"),
	BAHRAIN("Bahrein"),
	BANGLADESH("Banglades"),
	BARBADOS("Barbados"),
	BELARUS("Fehéroroszország"),
	BELGIUM("Belgium"),
	BELIZE("Belize"),
	BENIN("Benin"),
	BHUTAN("Bhután"),
	BOLIVIA("Bolívia"),
	BOSNIA_AND_HERZEGOVINA("Bosznia-Hercegovina"),
	BOTSWANA("Botswana"),
	BRAZIL("Brazília"),
	BRUNEI("Brunei"),
	BULGARIA("Bulgária"),
	BURKINA_FASO("Burkina Faso"),
	BURUNDI("Burundi"),
	CAMBODIA("Kambodzsa"),
	CAMEROON("Kamerun"),
	CENTRAL_AFRICAN_REPUBLIC("Közép-Afrikai Köztársaság"),
	CHAD("Csád"),
	CHILE("Chile"),
	CHINA("Kína"),
	COLOMBIA("Kolumbia"),
	COMOROS("Comore-szigetek"),
	CONGO("Kongó"),
	COSTA_RICA("Costa Rica"),
	CUBA("Kuba"),
	DJIBOUTI("Dzsibuti"),
	DOMINICA("Dominika"),
	ECUADOR("Ecuador"),
	EGYPT("Egyiptom"),
	EL_SALVADOR("Salvador"),
	EQUATORIAL_GUINEA("Egyenlítői-Guinea"),
	ERITREA("Eritrea"),
	ETHIOPIA("Etiópia"),
	FIJI("Fidzsi-szigetek"),
	GABON("Gabon"),
	GAMBIA("Gambia"),
	GEORGIA("Grúzia"),
	GHANA("Ghána"),
	GRENADA("Grenada"),
	GUATEMALA("Guatemala"),
	GUINEA("Guinea"),
	GUYANA("Guyana"),
	HAITI("Haiti"),
	HONDURAS("Honduras"),
	INDIA("India"),
	INDONESIA("Indonézia"),
	IRAN("Irán"),
	IRAQ("Irak"),
	ISRAEL("Izrael"),
	JAMAICA("Jamaica"),
	JAPAN("Japán"),
	JORDAN("Jordánia"),
	KAZAKHSTAN("Kazahsztán"),
	KENYA("Kenya"),
	KIRIBATI("Kiribati"),
	KUWAIT("Kuwait"),
	KYRGYZSTAN("Kirgizisztán"),
	LAOS("Laosz"),
	LEBANON("Libanon"),
	LESOTHO("Lesotho"),
	LIBERIA("Libéria"),
	LIBYA("Líbia"),
	LIECHTENSTEIN("Lichtenstein"),
	MADAGASCAR("Madagaszkár"),
	MALAWI("Malawi"),
	MALAYSIA("Malajzia"),
	MALDIVES("Maldív-szigetek"),
	MALI("Mali"),
	MARSHALL_ISLANDS("Marshall-szigetek"),
	MAURITANIA("Mauritánia"),
	MAURITIUS("Mauritius"),
	MEXICO("Mexikó"),
	MICRONESIA("Mikronézia"),
	MOLDOVA("Moldova"),
	MONGOLIA("Mongólia"),
	MOROCCO("Marokkó"),
	MOZAMBIQUE("Mozambik"),
	MYANMAR("Mianmar"),
	NAMIBIA("Namíbia"),
	NAURU("Nauru"),
	NEPAL("Nepál"),
	NEW_ZEALAND("Új-Zéland"),
	NICARAGUA("Nicaragua"),
	NIGER("Niger"),
	NIGERIA("Nigéria"),
	NORTH_KOREA("Észak-Korea"),
	NORTH_MACEDONIA("Észak-Macedónia"),
	OMAN("Omán"),
	PAKISTAN("Pakisztán"),
	PALAU("Palau"),
	PALESTINE_STATE("Palesztína"),
	PANAMA("Panama"),
	PAPUA_NEW_GUINEA("Pápua Új-Guinea"),
	PARAGUAY("Paraguay"),
	PERU("Peru"),
	PHILIPPINES("Fülöp-szigetek"),
	QATAR("Katar"),
	RUSSIA("Oroszország"),
	RWANDA("Ruanda"),
	SAMOA("Szamoa"),
	SAO_TOME_AND_PRINCIPE("São Tomé és Príncipe"),
	SAUDI_ARABIA("Szaúd-Arábia"),
	SENEGAL("Szenegál"),
	SERBIA("Szerbia"),
	SEYCHELLES("Seychelle-szigetek"),
	SIERRA_LEONE("Sierra Leone"),
	SINGAPORE("Szingapúr"),
	SOLOMON_ISLANDS("Salamon-szigetek"),
	SOMALIA("Szomália"),
	SOUTH_AFRICA("Dél-Afrika"),
	SOUTH_KOREA("Dél-Korea"),
	SOUTH_SUDAN("Dél-Szudán"),
	SRI_LANKA("Srí Lanka"),
	SUDAN("Szudán"),
	SURINAME("Suriname"),
	SWITZERLAND("Svájc"),
	SYRIA("Szíria"),
	TAJIKISTAN("Tadzsikisztán"),
	TANZANIA("Tanzánia"),
	THAILAND("Thaiföld"),
	TOGO("Togo"),
	TONGA("Tonga"),
	TRINIDAD_AND_TOBAGO("Trinidad és Tobago"),
	TUNISIA("Tunézia"),
	TURKMENISTAN("Türkmenisztán"),
	TUVALU("Tuvalu"),
	UGANDA("Uganda"),
	UKRAINE("Ukrajna"),
	UNITED_ARAB_EMIRATES("Egyesült Arab Emírségek"),
	URUGUAY("Uruguay"),
	UZBEKISTAN("Üzbegisztán"),
	VANUATU("Vanuatu"),
	VENEZUELA("Venezuela"),
	VIETNAM("Vietnám"),
	YEMEN("Jemen"),
	ZAMBIA("Zambia"),
	ZIMBABWE("Zimbabwe");
	
	
	
	private final String countryHunName;
	private final static String[] countryHunNames = new String[Countries.values().length];

	private Countries(String countryHunName) {
		this.countryHunName = countryHunName;
	}

	public String getCountryHunName() {
		return countryHunName;
	}
	
	public static String[] getcountryHunNameList() {
		for (int i = 0; i < Countries.values().length; i++) {
			countryHunNames[i] = Countries.values()[i].getCountryHunName();
		}
		Arrays.sort(countryHunNames);
		return countryHunNames;
	}
	
}
