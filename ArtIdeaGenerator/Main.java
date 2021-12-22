package ArtIdeaGenerator;

import java.sql.*;

public class Main {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/art_idea_prompts";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static final String ADJECTIVE_TABLE_NAME = "adjectives";
    private static final String SUBJECTS_TABLE_NAME = "subject_prompt";
    private static final String FURTHER_DESCRIPTION_TABLE_NAME = "further_descriptions";
    private static final String PORTRAIT_SUBJECT_TABLE_NAME = "portrait_subjects";
    private static final String HAIR_TYPES_TABLE_NAME = "hair_types";
    private static final String FACE_ADDITIONS_TABLE_NAME = "face_additions";

    private static final String FACE_DESCRIPTION_TABLE_NAME = "face_descriptions";

    public static void main(String[] args) throws Exception {

        Connection connection = DriverManager
                .getConnection(CONNECTION_URL, USER, PASSWORD);

        GUI gui = new GUI();
        gui.createGUI(connection);

    }

    public static String generatePortrait(Connection connection) throws SQLException {
        Statement faceDescriptionStatement = connection.createStatement();
        Statement portraitSubjectStatement = connection.createStatement();
        Statement hairTypeStatement = connection.createStatement();
        Statement faceAdditionStatement = connection.createStatement();

        ResultSet faceDescriptions = createResultSetFromDB(faceDescriptionStatement, FACE_DESCRIPTION_TABLE_NAME);
        faceDescriptions.next();
        String faceDescription = faceDescriptions.getString("face_description");

        ResultSet portraitSubjects = createResultSetFromDB(portraitSubjectStatement, PORTRAIT_SUBJECT_TABLE_NAME);
        portraitSubjects.next();
        String portraitSubject = portraitSubjects.getString("portrait_subject");

        ResultSet hairTypes = createResultSetFromDB(hairTypeStatement, HAIR_TYPES_TABLE_NAME);
        hairTypes.next();
        String hairType = hairTypes.getString("hair_type");

        ResultSet faceAdditions = createResultSetFromDB(faceAdditionStatement, FACE_ADDITIONS_TABLE_NAME);
        faceAdditions.next();
        String faceAddition = faceAdditions.getString("face_addition");

        return generatePortraitSentence(faceDescription, portraitSubject, hairType, faceAddition);
    }

    public static String generatePortraitSentence(String faceDescription, String portraitSubject, String hairType, String faceAddition) {
        String indefiniteArticle = "A";
        if (adjectiveStartsWithVowel(faceDescription)) {
            indefiniteArticle = "An";
        }

        return String.format("%s %s %s with %s hair and %s", indefiniteArticle, faceDescription, portraitSubject, hairType, faceAddition);

    }


    public static String generateCharacter(Connection connection) throws SQLException {
        Statement adjectiveStatement = connection.createStatement();
        Statement subjectStatement = connection.createStatement();
        Statement additionalDescriptionStatement = connection.createStatement();

        ResultSet adjectives = createResultSetFromDB(adjectiveStatement, ADJECTIVE_TABLE_NAME);
        adjectives.next();
        String adjective = adjectives.getString("adjective");

        ResultSet subjects = createResultSetFromDB(subjectStatement, SUBJECTS_TABLE_NAME);
        subjects.next();
        String subject = subjects.getString("subject_idea");

        ResultSet additionalDescriptions = createResultSetFromDB(additionalDescriptionStatement, FURTHER_DESCRIPTION_TABLE_NAME);
        additionalDescriptions.next();
        String additionalDescription = additionalDescriptions.getString("further_description");

        return generateSentence(adjective, subject, additionalDescription);
    }

    private static ResultSet createResultSetFromDB(Statement statement, String tableName) throws SQLException {
        String query = String.format("select * from %s ORDER BY RAND() LIMIT 1;", tableName);
        return statement.executeQuery(query);
    }

    private static String generateSentence(String adjective, String subject, String additionalDescription) {
        String indefiniteArticle = "A";
        if (adjectiveStartsWithVowel(adjective)) {
            indefiniteArticle = "An";
        }
        return String.format("%s %s %s %s", indefiniteArticle, adjective, subject, additionalDescription);
    }

    private static boolean adjectiveStartsWithVowel(String adjective) {
        char firstLetter = adjective.charAt(0);
        return firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i' || firstLetter == 'o' || firstLetter == 'u';
    }


}
