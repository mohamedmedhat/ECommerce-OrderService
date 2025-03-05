-- Step 1: Add a new UUID column
ALTER TABLE "orders" ADD COLUMN "id" UUID DEFAULT uuid_generate_v4() PRIMARY KEY;