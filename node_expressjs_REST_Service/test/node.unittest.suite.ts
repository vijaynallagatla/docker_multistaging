import { expect } from "chai";
import * as express from "express";
import * as supertest from "supertest";
const router: express.Router = express.Router({ caseSensitive: true });
function importTest(name, path) {
    describe(name, () => {
        require(path);
    });
}

describe("Unit Test", () => {
    let server: any;

    before(() => {
        server = require("../src/index");
    });

    it("Should start express server and respond to '/' route", async () => {
        const result = await supertest(server).get("/");
        expect(result.text).to.be.equal("Hello world!");
    });

    afterEach(() => {
        server.close();
    });
});
