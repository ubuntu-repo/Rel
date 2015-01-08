// Version 0.3.4 Database Backup Script

WRITELN "/*** Rel Database Backup ***/";
WRITELN;

WRITELN "// Created in Rel Version " || (ver FROM TUPLE FROM (EXTEND sys.Version : {ver := major || "." || minor || "." || revision || " " || release}));
WRITELN "// Using DatabaseToScript version 0.3.5";
WRITELN;

WRITELN "BEGIN TRANSACTION;";
WRITELN;

BEGIN TRANSACTION;

FOR
	UNION {
		EXTEND sys.Catalog {Name, Owner, CreationSequence, Definition, isVirtual} WHERE Owner <> "Rel" : {ProcessSequence := 0, objectType := "var"},
		EXTEND ((sys.Operators UNGROUP Implementations) WHERE CreatedByType="") {Name, Owner, CreationSequence, Definition} WHERE Owner <> "Rel" : {ProcessSequence := 0, objectType := "operator", isVirtual := false},
		EXTEND sys.Types {Name, Owner, CreationSequence, Definition} WHERE Owner <> "Rel" : {ProcessSequence := 0, objectType := "type", isVirtual := false},
		EXTEND sys.Constraints {Name, Owner, CreationSequence, Definition} WHERE Owner <> "Rel" : {ProcessSequence := 1, objectType := "constraint", isVirtual := false}
	} ORDER (ASC ProcessSequence, ASC CreationSequence);
BEGIN;
	WRITELN "ANNOUNCE '" || objectType || " " || Name || "';";
	CASE;
		WHEN objectType = "var" THEN
			BEGIN;
				WRITELN "VAR " || Name || " " || Definition || ";";
				IF NOT isVirtual THEN
					EXECUTE "IF COUNT(" || Name || ") > 0 THEN BEGIN; WRITE '" || Name || " := '; OUTPUT " || Name || "; WRITELN ';'; END; END IF;";
				END IF;
			END;
		WHEN objectType = "constraint" THEN WRITELN "CONSTRAINT " || Name || " " || Definition || ";";
		ELSE WRITELN Definition;
	END CASE;
	WRITELN;
END;
END FOR;

COMMIT;

WRITELN "COMMIT;";
WRITELN;

WRITELN "/*** End of Rel Database Backup ***/";

WRITELN "ANNOUNCE 'End of Script.';";