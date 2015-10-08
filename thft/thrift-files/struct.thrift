namespace java com.tektak.thft
struct Tweet{
    1: required i32 userId;
    2: required string userName;
    3: required string text;
    4: optional string language = "english"
    }