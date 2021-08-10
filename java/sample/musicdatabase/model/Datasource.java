package sample.musicdatabase.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\OldenErwanin\\JavaDevelopment\\DatabasePractice\\" + DB_NAME;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";
    public static final int INDEX_SONGS_ID = 1;
    public static final int INDEX_SONGS_TRACK = 2;
    public static final int INDEX_SONGS_TITLE = 3;
    public static final int INDEX_SONGS_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    // SELECT albums.name FROM albums INNER JOIN artist ON albums.artist = artists._id WHERE artists.name = "artistName" ORDER BY albums.name COLLATE NOCASE sortOrder
    public static final String QUERY_ALBUMS_BY_ARTISTS_START = "SELECT " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " FROM " + TABLE_ALBUMS +
                                                                " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_ARTIST +
                                                                " = " + TABLE_ARTISTS + '.' + COLUMN_ARTISTS_ID + " WHERE " + TABLE_ARTISTS + '.' + COLUMN_ARTISTS_NAME + " = \"";
    public static final String QUERY_ALBUMS_BY_ARTISTS_SORT = " ORDER BY " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    // SELECT artists.name, albums.name, songs.track FROM songs INNER JOIN albums ON songs.album = albums._id INNER JOIN artists ON albums.artist = artists._id WHERE songs.title = "Go Your Own Way" ORDER BY artists.name, albums.name COLLATE NOCASE ASC
    public static final String QUERY_ARTISTS_FOR_SONGS_START =
                                "SELECT " + TABLE_ARTISTS + '.' + COLUMN_ARTISTS_NAME + ", " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + ", " + TABLE_SONGS + '.' + COLUMN_SONGS_TRACK +
                                 " FROM " + TABLE_SONGS + " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + '.' + COLUMN_SONGS_ALBUM + " = " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_ID +
                                " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + '.' + COLUMN_ARTISTS_ID + " WHERE " +
                                TABLE_SONGS + '.' + COLUMN_SONGS_TITLE + " = \"";
    public static final String QUERY_ARTISTS_FOR_SONGS_SORT =
                                " ORDER BY " + TABLE_ARTISTS + '.' + COLUMN_ARTISTS_NAME + ", " + TABLE_ALBUMS + '.' + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    // CREATE VIEW IF NOT EXISTS artist_list AS SELECT artists.name AS album, songs.track, songs.title FROM songs INNER JOIN albums ON songs.album = albums._id INNER JOIN artists ON albums.artist = artists._id ORDER BY artists.name, albums.name, songs.track
    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " + TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME +
            ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + COLUMN_SONGS_ALBUM + ", " + TABLE_SONGS + "." + COLUMN_SONGS_TRACK + ", " + TABLE_SONGS + "." + COLUMN_SONGS_TITLE +
            " FROM " + TABLE_SONGS + " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONGS_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID + " INNER JOIN " +
            TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID + " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME +
            ", " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " + TABLE_SONGS + "." + COLUMN_SONGS_TRACK;

    // SELECT name, album, track FROM artist_list WHERE title = "title"
    public static final String QUERY_VIEW_SONG_INFO = "SELECT " + COLUMN_ARTISTS_NAME + ", " + COLUMN_SONGS_ALBUM + ", " + COLUMN_SONGS_TRACK + " FROM " +
            TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONGS_TITLE + " = \"";

    // SELECT name, album, track FROM artist_list WHERE title = ?
    public static final String QUERY_VIEW_SONG_INFO_PREP = "SELECT " + COLUMN_ARTISTS_NAME + ", " + COLUMN_SONGS_ALBUM + ", " + COLUMN_SONGS_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW +
            " WHERE " + COLUMN_SONGS_TITLE + " = ?";

    // INSERT INTO artists (name) VALUES (?)
    public static final String INSERT_ARTISTS = "INSERT INTO " + TABLE_ARTISTS + '(' + COLUMN_ARTISTS_NAME + ") VALUES (?)";
    // INSERT INTO albums (name, artist) VALUES (?, ?)
    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS + '(' + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES (?, ?)";
    // INSERT INTO songs (track, title, album) VALUES (?, ?, ?)
    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS + '(' + COLUMN_SONGS_TRACK + ", " + COLUMN_SONGS_TITLE + ", " + COLUMN_SONGS_ALBUM + ") VALUES (?, ?, ?)";

    // SELECT _id FROM artists WHERE name = ?
    public static final String QUERY_ARTISTS = "SELECT " + COLUMN_ARTISTS_ID + " FROM " + TABLE_ARTISTS + " WHERE " + COLUMN_ARTISTS_NAME + " = ?";
    // SELECT _id FROM albums WHERE name = ?
    public static final String QUERY_ALBUMS = "SELECT " + COLUMN_ALBUM_ID + " FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_NAME + " = ?";

    // SELECT * FROM albums WHERE artist = ? ORDER BY name COLLATE NOCASE
    public static final String QUERY_ALBUMS_BY_ARTIST_ID = "SELECT * FROM " + TABLE_ALBUMS + " WHERE " + COLUMN_ALBUM_ARTIST + " = ? ORDER BY " + COLUMN_ALBUM_NAME + " COLLATE NOCASE";

    // UPDATE artists SET name = ? WHERE _id = ?
    public static final String UPDATE_ARTIST_NAME = "UPDATE " + TABLE_ARTISTS + " SET " + COLUMN_ARTISTS_NAME + " = ? WHERE " + COLUMN_ARTISTS_ID + " = ?";

    private Connection conn;

    private PreparedStatement querySongInfoView;
    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtists;
    private PreparedStatement queryAlbums;

    private PreparedStatement queryAlbumsByArtistId;
    private PreparedStatement updateArtistName;

    private static Datasource instance = new Datasource();
    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            querySongInfoView = conn.prepareStatement(QUERY_VIEW_SONG_INFO_PREP);
            insertIntoArtists = conn.prepareStatement(INSERT_ARTISTS, Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS, Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);
            queryAlbumsByArtistId = conn.prepareStatement(QUERY_ALBUMS_BY_ARTIST_ID);
            updateArtistName = conn.prepareStatement(UPDATE_ARTIST_NAME);

            queryArtists = conn.prepareStatement(QUERY_ARTISTS);
            queryAlbums = conn.prepareStatement(QUERY_ALBUMS);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (querySongInfoView != null) {
                querySongInfoView.close();
            }
            if (insertIntoArtists != null) {
                insertIntoArtists.close();
            }
            if (insertIntoAlbums != null) {
                insertIntoAlbums.close();
            }
            if (insertIntoSongs != null) {
                insertIntoSongs.close();
            }
            if (queryArtists != null) {
                queryArtists.close();
            }
            if (queryAlbums != null) {
                queryAlbums.close();
            }
            if (queryAlbumsByArtistId != null) {
                queryAlbumsByArtistId.close();
            }
            if (updateArtistName != null) {
                updateArtistName.close();
            }
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Artist> queryArtists(int sortOrder) {
        //Statement statement = null;
        //ResultSet results = null;
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTISTS_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())){

            //statement = conn.createStatement();
            //results = statement.executeQuery("SELECT * FROM " + TABLE_ARTISTS);

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted: " + e.getMessage());
                }
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTIST_ID));
                artist.setName(results.getString(INDEX_ARTIST_NAME));
                artists.add(artist);
            }
            return artists;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }/* finally {
            try {
                if (results != null) {
                    results.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing Statement: " + e.getMessage());
            }
        }*/
    }

    public List<Album> queryAlbumsForArtistId(int id) {
        try {
            queryAlbumsByArtistId.setInt(1, id);
            ResultSet results = queryAlbumsByArtistId.executeQuery();

            List<Album> albums = new ArrayList<>();
            while(results.next()) {
                Album album = new Album();
                album.setId(results.getInt(1));
                album.setName(results.getString(2));
                album.setArtistId(id);
                albums.add(album);
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTISTS_START);
        sb.append(artistName);
        sb.append("\"");
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTISTS_SORT);
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }
        System.out.println("SQL statement = " + sb.toString());
        try (Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())){
            List<String> albums = new ArrayList<>();
            while (results.next()) {
                albums.add(results.getString(1));
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void querySongsMetadata() {
        String sql = "SELECT * FROM " + TABLE_SONGS;
        try (Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql)) {

            ResultSetMetaData meta = results.getMetaData();
            int numColumns = meta.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d in the songs table is names %s\n", i, meta.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS count, MIN(_id) AS min_id FROM " + table;
        try (Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sql)) {
            int count = results.getInt("count");
            int min = results.getInt("min_id");
            System.out.println("Minimum value: " + min);
            return count;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return -1;
        }
    }

    public boolean createViewForSongArtists() {
        try (Statement statement = conn.createStatement()) {
            statement.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException e) {
            System.out.println("Create View failed: " + e.getMessage());
            return false;
        }
    }

    private int insertArtist(String name) throws SQLException {
        queryArtists.setString(1, name);
        ResultSet results = queryArtists.executeQuery();
        // Check if we already have the artist
        if (results.next()) {
            return results.getInt(1);
        } else {
            // Insert the artist
            insertIntoArtists.setString(1, name);
            // Get the affected row number (should be 1)
            int affectedRows = insertIntoArtists.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert artist!");
            }
            // Get the inserted row _id
            ResultSet generatedKeys = insertIntoArtists.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }

    private int insertAlbum(String name, int artistID) throws SQLException {
        queryAlbums.setString(1, name);

        ResultSet results = queryAlbums.executeQuery();
        // Check if we already have the artist
        if (results.next()) {
            return results.getInt(1);
        } else {
            // Insert the album
            insertIntoAlbums.setString(1, name);
            insertIntoAlbums.setInt(2, artistID);
            // Get the affected row number (should be 1)
            int affectedRows = insertIntoAlbums.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert album!");
            }
            // Get the inserted row _id
            ResultSet generatedKeys = insertIntoAlbums.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for album");
            }
        }
    }

    public boolean updateArtistName(int id, String newName) {
        try {
            updateArtistName.setString(1, newName);
            updateArtistName.setInt(2, id);
            int affectedRecords = updateArtistName.executeUpdate();
            return affectedRecords == 1;
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
            return false;
        }
    }

    public void insertSong(String title, String artist, String album, int track) {
        try {
            conn.setAutoCommit(false);
            int artistID = insertArtist(artist);
            int albumID = insertAlbum(album, artistID);
            insertIntoSongs.setInt(1, track);
            insertIntoSongs.setString(2, title);
            insertIntoSongs.setInt(3, albumID);

            int affectedRows = insertIntoSongs.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("The song insert failed!");
            }
        // Catch any kind of exception, any kind of error, prevent inserting
        } catch (Exception e) {
            System.out.println("Insert song exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Things are messed up: " + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behaviour");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit: " + e.getMessage());
            }
        }
    }
}
