package work.worker.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TestUtilsData {

    /**the string data of fantatic mr fox from the open libary api. */
    private String foxBookInfo =
    "{\"publishers\": [\"Puffin\"], \"number_of_pages\": 96, \"isbn_10\": "
    + "[\"0140328726\"], \"covers\": [8739161], \"key\": \"/books/OL7353617M\","
    + " \"authors\": [{\"key\": \"/authors/OL34184A\"}], \"ocaid\": "
    + "\"fantasticmrfoxpu00roal\","
    + " \"contributions\": [\"Tony Ross (Illustrator)\"], \"languages\": "
    + "[{\"key\": \"/languages/eng\"}], \"classifications\": {},"
    + " \"source_records\": [\"ia:fantasticmrfox00dahl_834\", "
    + "\"marc:marc_openlibraries_sanfranciscopubliclibrary/sfpl_"
    + "chq_2018_12_24_run02.mrc:85081404:4525\", \"amazon:0140328726\","
    + " \"bwb:9780140328721\", \"promise:bwb_daily_pallets_2021-05-13\", \"prom"
    + "ise:bwb_daily_pallets_2021-04-19\", \"promise:bwb_daily_pallets_20"
    + "20-04-30\", \"promise:bwb_daily_pallets_2021-06-28\"], \"title\": \"Fan"
    + "tastic Mr. Fox\", \"identifiers\": {\"goodreads\": [\"1507552\"], \"lib"
    + "rarything\": [\"6446\"], \"amazon\": [\"\"]}, \"isbn_13\": [\"97801403"
    + "28721\"], \"local_id\": [\"urn:sfpl:31223064402481\", \"urn:sfpl:31223"
    + "117624784\", \"urn:sfpl:31223113969183\", \"urn:sfpl:312231176"
    + "24800\", \"urn:sfpl:31223113969225\", \"urn:sfpl:31223"
    + "106484539\", \"urn:sf"
    + "pl:31223117624792\", \"urn:sfpl:31223117624818\", \"urn:sfpl:3122311762"
    + "4768\", \"urn:sfpl:31223117624743\", \"urn:sfpl:3122311396"
    + "9209\", \"urn:sfpl:31223117624750\", \"urn:sfpl:3122311762"
    + "4727\", \"urn:sfpl:31223117624776\", \"urn:sfpl:3122311762"
    + "4719\", \"urn:sfpl:31223117624735\", \"urn:sfpl:3122311396"
    + "9241\", \"urn:bwbsku:KP-140-654\", \"urn:bwbsku:KP-128-"
    + "107\", \"urn:bwbsku:O6-BTK-941\", \"urn:bwbsku:O7-CAP-"
    + "452\"], \"publish_date\": \"October 1, 1988\", \"wo"
    + "rks\": [{\"key\": \"/works/OL45804W\"}], \"type\": {\"k"
    + "ey\": \"/type/edition\"}, \"first_sentence\": {\"ty"
    + "pe\": \"/type/text\", \"value\": \"And these two ve"
    + "ry old people are the father and mother of Mrs. Buc"
    + "ket.\"}, \"latest_revision\": 21, \"revision\": 21, \"cre"
    + "ated\": {\"type\": \"/type/datetime\", \"value\": \"2008-"
    + "04-29T13:35:46.876380\"}, \"last_modified\": {\"ty"
    + "pe\": \"/type/datetime\", \"value\": \"2023-01-14T1"
    + "8:01:04.370498\"}}";

    /**json data for fox author. */
    private String foxAuthorJson =
    "{\"remote_ids\": {\"isni\": \"0000000121468"
    + "188\", \"viaf\": \"108159131\", \"wikid"
    + "ata\": \"Q25161\"}, \"personal_name\": \"Dahl, "
    + "Roald.\", \"links\": [{\"url\": \"http://www.roal"
    + "ddahl.com/\", \"title\": \"roalddahl.com\", \"ty"
    + "pe\": {\"key\": \"/type/link\"}}, {\"url\": \"http://en.wiki"
    + "pedia.org/wiki/Roald_Dahl\", \"title\": \"Wikipedia e"
    + "ntry\", \"type\": {\"key\": \"/type/link\"}}], \"death_d"
    + "ate\": \"23 November 1990\", \"type\": {\"key\": \"/type"
    + "/author\"}, \"key\": \"/authors/OL34184A\", \"photos\": [939"
    + "5323, 9395316, 9395314, 9395313, 6287214], \"bio\": \"Roal"
    + "d Dahl was a British novelist, short story writer, and scre"
    + "enwriter.\r\n\r\nBorn in north Cardiff, Wales, to Norwegian"
    + " parents, Dahl served in the Royal Air Force during the Sec"
    + "ond World War, in which he became a flying ace and intelligenc"
    + "e agent. He rose to prominence in the 1940s with works for bot"
    + "h children and adults, and became one of the world's bestsellin"
    + "g authors. His short stories are known for their unexpected end"
    + "ings, and his children's books for their unsentimental, often ve"
    + "ry dark humour. ([Source][1].)\r\n\r\n\r\n  [1]: http://en.wiki"
    + "pedia.org/wiki/Roald_Dahl\", \"name\": \"Roald Dahl\", \"sourc"
    + "e_records\": [\"amazon:8420440248\", \"amazon:8420401"
    + "307\", \"amazon:0670852503\", \"amazon:0140386068\", \"amaz"
    + "on:0140382518\", \"amazon:7533299299\", \"amazon:90261046"
    + "18\", \"amazon:0141326182\", \"amazon:0141371439\", \"amazo"
    + "n:0141348364\", \"amazon:0670882976\", \"amazon:0141348"
    + "348\", \"amazon:1760978302\", \"amazon:0141321954\", \"amaz"
    + "on:9877381915\", \"amazon:1849673241\", \"amazon:75332992"
    + "72\", \"amazon:9877384167\", \"amazon:1844227251\", \"bwb:97"
    + "80593528655\", \"amazon:8877820047\", \"amazon:14352552"
    + "67\", \"amazon:7533299264\", \"amazon:0375814248\", \"amazon"
    + ":0141352590\", \"amazon:0857551205\", \"promise:bwb_daily_p"
    + "allets_2022-03-17\"], \"birth_date\": \"13 September 19"
    + "16\", \"latest_revision\": 46, \"revision\": 46, \"creat"
    + "ed\": {\"type\": \"/type/datetime\", \"value\": \"2008-04-0"
    + "1T03:28:50.625462\"}, \"last_modified\": {\"type\": \"/type/da"
    + "tetime\", \"value\": \"2023-01-23T22:09:31.043530\"}}";
}
