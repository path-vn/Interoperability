using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Security.Cryptography;

namespace GlobitsHivInfoAdapter.Utils
{
    class HIVInfoSecurityUtil
    {
        public const string SECURITYKEY = "@1256398";

        public string Encrypt(string toEncrypt, bool useHashing)
        {
            //IL_000f: Unknown result type (might be due to invalid IL or missing references)
            //IL_0015: Expected O, but got Unknown
            //IL_0043: Unknown result type (might be due to invalid IL or missing references)
            //IL_0049: Expected O, but got Unknown
            byte[] bytes = Encoding.UTF8.GetBytes(toEncrypt);
            byte[] key;
            if (useHashing)
            {
                MD5CryptoServiceProvider val = new MD5CryptoServiceProvider();
                key = ((HashAlgorithm)val).ComputeHash(Encoding.UTF8.GetBytes("@1256398"));
                ((HashAlgorithm)val).Clear();
            }
            else
            {
                key = Encoding.UTF8.GetBytes("@1256398");
            }
            TripleDESCryptoServiceProvider val2 = new TripleDESCryptoServiceProvider();
            ((SymmetricAlgorithm)val2).Key = (key);
            ((SymmetricAlgorithm)val2).Mode = ((CipherMode)2);
            ((SymmetricAlgorithm)val2).Padding = ((PaddingMode)2);
            ICryptoTransform val3 = ((SymmetricAlgorithm)val2).CreateEncryptor();
            byte[] array = val3.TransformFinalBlock(bytes, 0, bytes.Length);
            ((SymmetricAlgorithm)val2).Clear();
            return Convert.ToBase64String(array, 0, array.Length);
        }

        public string Decrypt(string cipherString, bool useHashing)
        {
            try
            {
                //IL_000a: Unknown result type (might be due to invalid IL or missing references)
                //IL_0010: Expected O, but got Unknown
                //IL_003e: Unknown result type (might be due to invalid IL or missing references)
                //IL_0044: Expected O, but got Unknown
                byte[] array = Convert.FromBase64String(cipherString);
                byte[] key;
                if (useHashing)
                {
                    MD5CryptoServiceProvider val = new MD5CryptoServiceProvider();
                    key = ((HashAlgorithm)val).ComputeHash(Encoding.UTF8.GetBytes("@1256398"));
                    ((HashAlgorithm)val).Clear();
                }
                else
                {
                    key = Encoding.UTF8.GetBytes("@1256398");
                }
                TripleDESCryptoServiceProvider val2 = new TripleDESCryptoServiceProvider();
                ((SymmetricAlgorithm)val2).Key = key;
                ((SymmetricAlgorithm)val2).Mode = ((CipherMode)2);
                ((SymmetricAlgorithm)val2).Padding = ((PaddingMode)2);
                ICryptoTransform val3 = ((SymmetricAlgorithm)val2).CreateDecryptor();
                byte[] bytes = val3.TransformFinalBlock(array, 0, array.Length);
                ((SymmetricAlgorithm)val2).Clear();
                return Encoding.UTF8.GetString(bytes).Replace(";;", "");
            }
            catch (Exception ex)
            {
                return "";
            }
        }

        public static string Encrypt(string toEncrypt, bool useHashing, string strTuKhoaMaHoa)
        {
            //IL_000f: Unknown result type (might be due to invalid IL or missing references)
            //IL_0015: Expected O, but got Unknown
            //IL_003b: Unknown result type (might be due to invalid IL or missing references)
            //IL_0041: Expected O, but got Unknown
            byte[] bytes = Encoding.UTF8.GetBytes(toEncrypt);
            byte[] key;
            if (useHashing)
            {
                MD5CryptoServiceProvider val = new MD5CryptoServiceProvider();
                key = ((HashAlgorithm)val).ComputeHash(Encoding.UTF8.GetBytes(strTuKhoaMaHoa));
                ((HashAlgorithm)val).Clear();
            }
            else
            {
                key = Encoding.UTF8.GetBytes(strTuKhoaMaHoa);
            }
            TripleDESCryptoServiceProvider val2 = new TripleDESCryptoServiceProvider();
            ((SymmetricAlgorithm)val2).Key = (key);
            ((SymmetricAlgorithm)val2).Mode = ((CipherMode)2);
            ((SymmetricAlgorithm)val2).Padding = ((PaddingMode)2);
            ICryptoTransform val3 = ((SymmetricAlgorithm)val2).CreateEncryptor();
            byte[] array = val3.TransformFinalBlock(bytes, 0, bytes.Length);
            ((SymmetricAlgorithm)val2).Clear();
            return Convert.ToBase64String(array, 0, array.Length);
        }

        public static string Decrypt(string cipherString, bool useHashing, string strTuKhoaMaHoa)
        {
            //IL_000a: Unknown result type (might be due to invalid IL or missing references)
            //IL_0010: Expected O, but got Unknown
            //IL_0036: Unknown result type (might be due to invalid IL or missing references)
            //IL_003c: Expected O, but got Unknown
            byte[] array = Convert.FromBase64String(cipherString);
            byte[] key;
            if (useHashing)
            {
                MD5CryptoServiceProvider val = new MD5CryptoServiceProvider();
                key = ((HashAlgorithm)val).ComputeHash(Encoding.UTF8.GetBytes(strTuKhoaMaHoa));
                ((HashAlgorithm)val).Clear();
            }
            else
            {
                key = Encoding.UTF8.GetBytes(strTuKhoaMaHoa);
            }
            TripleDESCryptoServiceProvider val2 = new TripleDESCryptoServiceProvider();
            ((SymmetricAlgorithm)val2).Key = (key);
            ((SymmetricAlgorithm)val2).Mode = ((CipherMode)2);
            ((SymmetricAlgorithm)val2).Padding = ((PaddingMode)2);
            ICryptoTransform val3 = ((SymmetricAlgorithm)val2).CreateDecryptor();
            byte[] bytes = val3.TransformFinalBlock(array, 0, array.Length);
            ((SymmetricAlgorithm)val2).Clear();
            return Encoding.UTF8.GetString(bytes);
        }
    }
}
