alter table Ticket
    alter column qrCode set default (uuid());