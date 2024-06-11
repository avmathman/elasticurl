db = db.getSiblingDB("shorturl_db");

db.createUser({
    user: "test",
    pwd: "test",
    roles: [{
        role: "readWrite",
        db: "shorturl_db"
    }]
});

db.createCollection("shorturl_collection");