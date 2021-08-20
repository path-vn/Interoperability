using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Serialization;

namespace GlobitsHivInfoAdapter.Utils
{
    public class FileManager
    {
        /// <summary>
        /// Serializes an object.
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="serializableObject"></param>
        /// <param name="folderName"></param>
        /// <param name="fileName"></param>
        public void SerializeObject<T>(T serializableObject, string folderName, string fileName)
        {
            if (serializableObject == null) { return; }
            try
            {
                fileName += ".xml";
                var dateNow = DateTime.Now;
                //@"files\"
                string path = Path.Combine(Environment.CurrentDirectory, @"folderName\".Replace("folderName", folderName));
                new FileInfo(path).Directory.Create();
                path = Path.Combine(Environment.CurrentDirectory, folderName, fileName);

                XmlDocument xmlDocument = new XmlDocument();
                XmlSerializer serializer = new XmlSerializer(serializableObject.GetType());
                using (MemoryStream stream = new MemoryStream())
                {
                    serializer.Serialize(stream, serializableObject);
                    stream.Position = 0;
                    xmlDocument.Load(stream);
                    xmlDocument.Save(path);
                }
            }
            catch (Exception ex)
            {
                //Log exception here
            }
        }


        /// <summary>
        /// Deserializes an xml file into an object list
        /// </summary>
        /// <typeparam name="T"></typeparam>
        /// <param name="folderName"></param>
        /// <param name="fileName"></param>
        /// <returns></returns>
        public T DeSerializeObject<T>(string folderName, string fileName)
        {
            if (string.IsNullOrEmpty(fileName)) { return default(T); }

            T objectOut = default(T);

            try
            {
                fileName += ".xml";
                string path = Path.Combine(Environment.CurrentDirectory, @"folderName\".Replace("folderName", folderName));
                new FileInfo(path).Directory.Create();
                path = Path.Combine(Environment.CurrentDirectory, folderName, fileName);
                XmlDocument xmlDocument = new XmlDocument();
                xmlDocument.Load(path);
                string xmlString = xmlDocument.OuterXml;

                using (StringReader read = new StringReader(xmlString))
                {
                    Type outType = typeof(T);

                    XmlSerializer serializer = new XmlSerializer(outType);
                    using (XmlReader reader = new XmlTextReader(read))
                    {
                        objectOut = (T)serializer.Deserialize(reader);
                    }
                }
            }
            catch (Exception ex)
            {
                //Log exception here
            }

            return objectOut;
        }
    }
}
