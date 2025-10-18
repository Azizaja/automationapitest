# 🚀 Restful-Booker API Test Framework

Framework otomatisasi testing untuk [Restful-Booker API](https://restful-booker.herokuapp.com/apidoc/index.html) menggunakan **RestAssured** dan **TestNG**.

## 📋 Prerequisites

Sebelum memulai, pastikan Anda telah menginstall:

- **Java JDK 8+** (Disarankan JDK 11 atau 17)
- **Maven 3.6+**
- **IDE** (IntelliJ IDEA, Eclipse, atau VS Code)

## ⚙️ Installation

### 1. Clone atau Download Project

```bash
# Jika menggunakan git
git clone https://github.com/Azizaja/automationapitest
cd automationapitest

# Atau download sebagai ZIP dan extract
```

### 2. Setup Project di IDE

**IntelliJ IDEA:**
1. File → Open → Pilih folder project
2. Tunggu Maven dependencies terdownload otomatis
3. Set JDK: File → Project Structure → Project → SDK

**VS Code:**
1. File 

### 3. Verify Installation

```bash
# Test Maven installation
mvn --version

# Test Java installation
java -version
```

## 📦 Dependencies

Framework ini menggunakan:

- **RestAssured 5.5.6** - API testing
- **TestNG 7.11.0** - Test framework

Dependencies akan terdownload otomatis oleh Maven.

## 🚀 Running Tests

### 1. Run All Tests

```bash
mvn test
```

### 2. Run Specific Test Class

```bash
# Run AuthTest saja
mvn test -Dtest=

# Run BookingTest saja
mvn test -Dtest=

# Run multiple test classes
mvn test -Dtest=
```

### 3. Run Test Suite

```bash
mvn test -DsuiteXmlFile=testng.xml
```


### TestNG Configuration

Edit `testNG.xml` untuk mengubah test suite configuration:

```xml
<suite name="Suite1" verbose="1">
    <!-- Test classes configuration -->
</suite>
```

## 🐛 Troubleshooting

### Common Issues

1. **Dependencies not downloading**
   ```bash
   mvn clean compile
   ```

## 📄 License

This project is for educational purposes. Feel free to use and modify.

## 🔗 Documentation
CI/CD Pipeline : https://drive.google.com/drive/folders/1y_PXp2ZGuBy6HQim7CCMlLDy-zESzQXq?usp=sharing


## 🆘 Support

Jika mengalami issues:

1. Check existing issues di GitHub
2. Pastikan semua prerequisites terpenuhi
3. Verify API endpoint accessible
4. Check Maven dependencies

---

**Happy Testing!** 🎯