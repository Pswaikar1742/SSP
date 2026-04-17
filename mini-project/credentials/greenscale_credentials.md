# GreenScale Lite — Credentials

- **Database:** greenscale_db
- **SQL script:** [greenscale_db.sql](greenscale_db.sql)

## Admin

- **Username:** admin
- **Password (plaintext):** adminpass
- **Role:** admin
 - **Password (bcrypt hash):** $2a$12$inR3X4w1X9DpPasANvNQ4.z9PnJpAGeO6QUDzT5rkQCec8X.4eSC2

## Nodes

- **nobara-node-01:** CPU: 8 cores; RAM: 32GB; Storage: 1TB NVMe — **Status:** Available
- **nobara-node-02:** CPU: 16 cores; RAM: 64GB; Storage: 2TB NVMe — **Status:** Available
- **nobara-node-03:** CPU: 24 cores; RAM: 128GB; Storage: 4TB NVMe — **Status:** Available

## Notes

 - The admin password is stored in plaintext here for convenience; this file also includes an example bcrypt hash. Replace with your own secure hash before production.
- To import the database locally run:

```bash
mysql -u root -p < greenscale_db.sql
```
